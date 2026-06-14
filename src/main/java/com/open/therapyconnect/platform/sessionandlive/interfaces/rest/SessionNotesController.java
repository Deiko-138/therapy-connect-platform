package com.open.therapyconnect.platform.sessionandlive.interfaces.rest;

import com.open.therapyconnect.platform.sessionandlive.application.commandservices.SessionNoteCommandService;
import com.open.therapyconnect.platform.sessionandlive.application.queryservices.SessionNoteQueryService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.DeleteSessionNoteCommand;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.*;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import com.open.therapyconnect.platform.shared.interfaces.rest.resources.MessageResource;
import com.open.therapyconnect.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/session-notes", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Session Notes", description = "Session notes management endpoints")
public class SessionNotesController {

    private final SessionNoteCommandService commandService;
    private final SessionNoteQueryService queryService;

    public SessionNotesController(SessionNoteCommandService commandService,
                                   SessionNoteQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Create a session note")
    public ResponseEntity<?> createSessionNote(@RequestBody CreateSessionNoteResource resource) {
        var command = CreateSessionNoteCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command)
                .flatMap(id -> queryService.handle(new GetSessionNoteByIdQuery(id))
                        .<Result<com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionNote, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("SessionNote", id.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SessionNoteResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{noteId}")
    @Operation(summary = "Get session note by ID")
    public ResponseEntity<SessionNoteResource> getSessionNoteById(@PathVariable Long noteId) {
        var note = queryService.handle(new GetSessionNoteByIdQuery(noteId));
        if (note.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SessionNoteResourceFromEntityAssembler.toResourceFromEntity(note.get()));
    }

    @GetMapping("/session/{sessionId}")
    @Operation(summary = "Get all notes for a session")
    public ResponseEntity<List<SessionNoteResource>> getNotesBySessionId(@PathVariable Long sessionId) {
        var notes = queryService.handle(new GetNotesBySessionIdQuery(sessionId));
        var resources = notes.stream()
                .map(SessionNoteResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{noteId}")
    @Operation(summary = "Update session note")
    public ResponseEntity<?> updateSessionNote(@PathVariable Long noteId,
                                                @RequestBody UpdateSessionNoteResource resource) {
        var command = UpdateSessionNoteCommandFromResourceAssembler.toCommandFromResource(noteId, resource);
        var result = commandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SessionNoteResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{noteId}")
    @Operation(summary = "Delete session note")
    public ResponseEntity<?> deleteSessionNote(@PathVariable Long noteId) {
        var command = new DeleteSessionNoteCommand(noteId);
        var result = commandService.handle(command)
                .map(id -> new MessageResource("Session note with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(result, message -> message, HttpStatus.OK);
    }
}
