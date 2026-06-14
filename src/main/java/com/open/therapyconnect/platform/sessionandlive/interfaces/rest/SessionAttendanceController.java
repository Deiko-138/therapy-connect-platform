package com.open.therapyconnect.platform.sessionandlive.interfaces.rest;

import com.open.therapyconnect.platform.sessionandlive.application.commandservices.SessionAttendanceCommandService;
import com.open.therapyconnect.platform.sessionandlive.application.queryservices.SessionAttendanceQueryService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.DeleteAttendanceCommand;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.UpdateAttendanceCommand;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;
import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.AttendanceStatus;
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
@RequestMapping(value = "/api/v1/session-attendance", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Session Attendance", description = "Session attendance tracking endpoints")
public class SessionAttendanceController {

    private final SessionAttendanceCommandService commandService;
    private final SessionAttendanceQueryService queryService;

    public SessionAttendanceController(SessionAttendanceCommandService commandService,
                                        SessionAttendanceQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Record attendance for a session")
    public ResponseEntity<?> recordAttendance(@RequestBody RecordAttendanceResource resource) {
        var command = RecordAttendanceCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command)
                .flatMap(id -> queryService.handle(new GetAttendanceByIdQuery(id))
                        .<Result<com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionAttendance, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("SessionAttendance", id.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                AttendanceResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/session/{sessionId}")
    @Operation(summary = "Get attendance records by session ID")
    public ResponseEntity<List<AttendanceResource>> getAttendanceBySessionId(@PathVariable Long sessionId) {
        var records = queryService.handle(new GetAttendanceBySessionIdQuery(sessionId));
        var resources = records.stream()
                .map(AttendanceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get attendance records by student ID")
    public ResponseEntity<List<AttendanceResource>> getAttendanceByStudentId(@PathVariable Long studentId) {
        var records = queryService.handle(new GetAttendanceByStudentIdQuery(studentId));
        var resources = records.stream()
                .map(AttendanceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{attendanceId}")
    @Operation(summary = "Update attendance record")
    public ResponseEntity<?> updateAttendance(@PathVariable Long attendanceId,
                                               @RequestBody UpdateAttendanceResource resource) {
        var command = new UpdateAttendanceCommand(
                attendanceId,
                AttendanceStatus.valueOf(resource.attendanceStatus().toUpperCase()),
                resource.remarks()
        );
        var result = commandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                AttendanceResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{attendanceId}")
    @Operation(summary = "Delete attendance record")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long attendanceId) {
        var command = new DeleteAttendanceCommand(attendanceId);
        var result = commandService.handle(command)
                .map(id -> new MessageResource("Attendance record with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(result, message -> message, HttpStatus.OK);
    }
}
