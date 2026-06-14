package com.open.therapyconnect.platform.sessionandlive.interfaces.rest;

import com.open.therapyconnect.platform.sessionandlive.application.queryservices.LiveSessionQueryService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.GetLiveSessionsByStudentIdQuery;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.GetLiveSessionsByTeacherIdQuery;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.LiveSessionResource;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform.LiveSessionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Read-only controller exposing session history derived from live session data.
 */
@RestController
@RequestMapping(value = "/api/v1/session-history", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Session History", description = "Session history retrieval endpoints")
public class SessionHistoryController {

    private final LiveSessionQueryService liveSessionQueryService;

    public SessionHistoryController(LiveSessionQueryService liveSessionQueryService) {
        this.liveSessionQueryService = liveSessionQueryService;
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get session history for a student")
    public ResponseEntity<List<LiveSessionResource>> getSessionHistoryByStudent(@PathVariable Long studentId) {
        var sessions = liveSessionQueryService.handle(new GetLiveSessionsByStudentIdQuery(studentId));
        var resources = sessions.stream()
                .map(LiveSessionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/teacher/{teacherId}")
    @Operation(summary = "Get session history for a teacher")
    public ResponseEntity<List<LiveSessionResource>> getSessionHistoryByTeacher(@PathVariable Long teacherId) {
        var sessions = liveSessionQueryService.handle(new GetLiveSessionsByTeacherIdQuery(teacherId));
        var resources = sessions.stream()
                .map(LiveSessionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
