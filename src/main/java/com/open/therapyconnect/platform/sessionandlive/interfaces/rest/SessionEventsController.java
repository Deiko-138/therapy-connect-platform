package com.open.therapyconnect.platform.sessionandlive.interfaces.rest;

import com.open.therapyconnect.platform.sessionandlive.application.commandservices.SessionEventCommandService;
import com.open.therapyconnect.platform.sessionandlive.application.queryservices.SessionEventQueryService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.DeleteSessionEventCommand;
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
@RequestMapping(value = "/api/v1/session-events", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Session Events", description = "Calendar session event endpoints")
public class SessionEventsController {

    private final SessionEventCommandService commandService;
    private final SessionEventQueryService queryService;

    public SessionEventsController(SessionEventCommandService commandService,
                                    SessionEventQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Create a session calendar event")
    public ResponseEntity<?> createSessionEvent(@RequestBody CreateSessionEventResource resource) {
        var command = CreateSessionEventCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command)
                .flatMap(id -> queryService.handle(new GetSessionEventByIdQuery(id))
                        .<Result<com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionEvent, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("SessionEvent", id.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SessionEventResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{eventId}")
    @Operation(summary = "Get session event by ID")
    public ResponseEntity<SessionEventResource> getSessionEventById(@PathVariable Long eventId) {
        var event = queryService.handle(new GetSessionEventByIdQuery(eventId));
        if (event.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SessionEventResourceFromEntityAssembler.toResourceFromEntity(event.get()));
    }

    @GetMapping
    @Operation(summary = "Get all session events")
    public ResponseEntity<List<SessionEventResource>> getAllSessionEvents() {
        var events = queryService.handle(new GetAllSessionEventsQuery());
        var resources = events.stream()
                .map(SessionEventResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/teacher/{teacherId}")
    @Operation(summary = "Get session events by teacher ID")
    public ResponseEntity<List<SessionEventResource>> getEventsByTeacherId(@PathVariable Long teacherId) {
        var events = queryService.handle(new GetEventsByTeacherIdQuery(teacherId));
        var resources = events.stream()
                .map(SessionEventResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get session events within a date range")
    public ResponseEntity<List<SessionEventResource>> getEventsByDateRange(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        var events = queryService.handle(new GetEventsByDateRangeQuery(startDate, endDate));
        var resources = events.stream()
                .map(SessionEventResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{eventId}")
    @Operation(summary = "Update session event")
    public ResponseEntity<?> updateSessionEvent(@PathVariable Long eventId,
                                                 @RequestBody UpdateSessionEventResource resource) {
        var command = UpdateSessionEventCommandFromResourceAssembler.toCommandFromResource(eventId, resource);
        var result = commandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SessionEventResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{eventId}")
    @Operation(summary = "Delete session event")
    public ResponseEntity<?> deleteSessionEvent(@PathVariable Long eventId) {
        var command = new DeleteSessionEventCommand(eventId);
        var result = commandService.handle(command)
                .map(id -> new MessageResource("Session event with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(result, message -> message, HttpStatus.OK);
    }
}
