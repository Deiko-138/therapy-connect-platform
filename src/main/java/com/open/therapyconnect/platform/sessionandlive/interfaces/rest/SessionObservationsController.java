package com.open.therapyconnect.platform.sessionandlive.interfaces.rest;

import com.open.therapyconnect.platform.sessionandlive.application.commandservices.SessionObservationCommandService;
import com.open.therapyconnect.platform.sessionandlive.application.queryservices.SessionObservationQueryService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.DeleteSessionObservationCommand;
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
@RequestMapping(value = "/api/v1/session-observations", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Session Observations", description = "Child progress observation endpoints")
public class SessionObservationsController {

    private final SessionObservationCommandService commandService;
    private final SessionObservationQueryService queryService;

    public SessionObservationsController(SessionObservationCommandService commandService,
                                          SessionObservationQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Create a session observation")
    public ResponseEntity<?> createSessionObservation(@RequestBody CreateSessionObservationResource resource) {
        var command = CreateSessionObservationCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command)
                .flatMap(id -> queryService.handle(new GetSessionObservationByIdQuery(id))
                        .<Result<com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionObservation, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("SessionObservation", id.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SessionObservationResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{observationId}")
    @Operation(summary = "Get observation by ID")
    public ResponseEntity<SessionObservationResource> getObservationById(@PathVariable Long observationId) {
        var observation = queryService.handle(new GetSessionObservationByIdQuery(observationId));
        if (observation.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SessionObservationResourceFromEntityAssembler.toResourceFromEntity(observation.get()));
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get observations by student ID")
    public ResponseEntity<List<SessionObservationResource>> getObservationsByStudentId(@PathVariable Long studentId) {
        var observations = queryService.handle(new GetObservationsByStudentIdQuery(studentId));
        var resources = observations.stream()
                .map(SessionObservationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/session/{sessionId}")
    @Operation(summary = "Get observations by session ID")
    public ResponseEntity<List<SessionObservationResource>> getObservationsBySessionId(@PathVariable Long sessionId) {
        var observations = queryService.handle(new GetObservationsBySessionIdQuery(sessionId));
        var resources = observations.stream()
                .map(SessionObservationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{observationId}")
    @Operation(summary = "Update session observation")
    public ResponseEntity<?> updateSessionObservation(@PathVariable Long observationId,
                                                       @RequestBody UpdateSessionObservationResource resource) {
        var command = UpdateSessionObservationCommandFromResourceAssembler.toCommandFromResource(observationId, resource);
        var result = commandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                SessionObservationResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{observationId}")
    @Operation(summary = "Delete session observation")
    public ResponseEntity<?> deleteSessionObservation(@PathVariable Long observationId) {
        var command = new DeleteSessionObservationCommand(observationId);
        var result = commandService.handle(command)
                .map(id -> new MessageResource("Session observation with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(result, message -> message, HttpStatus.OK);
    }
}
