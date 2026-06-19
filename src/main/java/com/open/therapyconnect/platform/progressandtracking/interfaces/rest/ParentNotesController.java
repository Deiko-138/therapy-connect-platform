package com.open.therapyconnect.platform.progressandtracking.interfaces.rest;

import com.open.therapyconnect.platform.progressandtracking.application.commandservices.ParentNoteCommandService;
import com.open.therapyconnect.platform.progressandtracking.application.queryservices.ParentNoteQueryService;
import com.open.therapyconnect.platform.progressandtracking.domain.model.commands.DeleteNoteCommand;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllParentNotesByAuthorProfileIdQuery;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllParentNotesQuery;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetParentNoteByIdQuery;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources.CreateParentNoteResource;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources.ParentNoteResource;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources.UpdateParentNoteResource;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.transform.CreateParentNoteCommandFromResourceAssembler;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.transform.ParentNoteResourceFromEntityAssembler;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.transform.UpdateParentNoteCommandFromResourceAssembler;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import com.open.therapyconnect.platform.shared.interfaces.rest.resources.MessageResource;
import com.open.therapyconnect.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller that exposes parent note endpoints.
 */
@RestController
@RequestMapping(value = "/api/v1/notes/parent", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Parent Notes", description = "Parent note management endpoints")
public class ParentNotesController {

    private final ParentNoteCommandService parentNoteCommandService;
    private final ParentNoteQueryService parentNoteQueryService;

    /**
     * Constructor
     *
     * @param parentNoteCommandService The {@link ParentNoteCommandService} instance
     * @param parentNoteQueryService   The {@link ParentNoteQueryService} instance
     */
    public ParentNotesController(
            ParentNoteCommandService parentNoteCommandService,
            ParentNoteQueryService parentNoteQueryService) {
        this.parentNoteCommandService = parentNoteCommandService;
        this.parentNoteQueryService = parentNoteQueryService;
    }

    /**
     * Create a new parent note
     *
     * @param resource The {@link CreateParentNoteResource} instance
     * @return The {@link ParentNoteResource} resource for the created parent note
     */
    @PostMapping
    @Operation(summary = "Create a new parent note", description = "Creates a new note written by a parent.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Parent note created successfully",
                    content = @Content(schema = @Schema(implementation = ParentNoteResource.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<?> createParentNote(@RequestBody CreateParentNoteResource resource) {
        var createParentNoteCommand = CreateParentNoteCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = parentNoteCommandService.handle(createParentNoteCommand)
                .flatMap(noteId -> parentNoteQueryService.handle(new GetParentNoteByIdQuery(noteId))
                        .<Result<com.open.therapyconnect.platform.progressandtracking.domain.model.aggregates.ParentNote, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("ParentNote", noteId.toString()))));

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ParentNoteResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    /**
     * Get parent note by id
     *
     * @param noteId The parent note id
     * @return The {@link ParentNoteResource} resource for the parent note
     */
    @GetMapping("/{noteId}")
    @Operation(summary = "Get parent note by ID", description = "Retrieves a specific parent note by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Parent note found",
                    content = @Content(schema = @Schema(implementation = ParentNoteResource.class))
            ),
            @ApiResponse(responseCode = "404", description = "Parent note not found")
    })
    public ResponseEntity<ParentNoteResource> getParentNoteById(
            @PathVariable
            @Parameter(description = "Unique parent note identifier", example = "1", required = true)
            Long noteId
    ) {
        var query = new GetParentNoteByIdQuery(noteId);
        var parentNote = parentNoteQueryService.handle(query);
        if (parentNote.isEmpty()) return ResponseEntity.notFound().build();
        var resource = ParentNoteResourceFromEntityAssembler.toResourceFromEntity(parentNote.get());
        return ResponseEntity.ok(resource);
    }

    /**
     * Get all parent notes
     *
     * @return The list of {@link ParentNoteResource} resources for all parent notes
     */
    @GetMapping
    @Operation(summary = "Get all parent notes", description = "Retrieves all parent notes. Filter by authorProfileId query param if provided.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Parent notes retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ParentNoteResource.class))
            )
    })
    public ResponseEntity<List<ParentNoteResource>> getAllParentNotes(
            @RequestParam(required = false)
            @Parameter(description = "Filter by author profile id (IAM reference)", example = "15")
            Long authorProfileId
    ) {
        var parentNotes = authorProfileId != null
                ? parentNoteQueryService.handle(new GetAllParentNotesByAuthorProfileIdQuery(authorProfileId))
                : parentNoteQueryService.handle(new GetAllParentNotesQuery());

        var resources = parentNotes.stream()
                .map(ParentNoteResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Update parent note
     *
     * @param noteId   The parent note id
     * @param resource The {@link UpdateParentNoteResource} instance
     * @return The {@link ParentNoteResource} resource for the updated parent note
     */
    @PutMapping("/{noteId}")
    @Operation(summary = "Update parent note", description = "Updates an existing parent note.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Parent note updated successfully",
                    content = @Content(schema = @Schema(implementation = ParentNoteResource.class))
            ),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Parent note not found")
    })
    public ResponseEntity<?> updateParentNote(
            @PathVariable
            @Parameter(description = "Unique parent note identifier", example = "1", required = true)
            Long noteId,
            @RequestBody UpdateParentNoteResource resource
    ) {
        var updateParentNoteCommand = UpdateParentNoteCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = parentNoteCommandService.handle(noteId, updateParentNoteCommand);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ParentNoteResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    /**
     * Delete parent note
     *
     * @param noteId The parent note id
     * @return The message for the deleted parent note
     */
    @DeleteMapping("/{noteId}")
    @Operation(summary = "Delete parent note", description = "Deletes a parent note by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Parent note deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Parent note not found")
    })
    public ResponseEntity<?> deleteParentNote(
            @PathVariable
            @Parameter(description = "Unique parent note identifier", example = "1", required = true)
            Long noteId
    ) {
        var deleteNoteCommand = new DeleteNoteCommand(noteId);
        var result = parentNoteCommandService.handle(deleteNoteCommand)
                .map(_ -> new MessageResource("Parent note with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                message -> message,
                HttpStatus.OK
        );
    }
}
