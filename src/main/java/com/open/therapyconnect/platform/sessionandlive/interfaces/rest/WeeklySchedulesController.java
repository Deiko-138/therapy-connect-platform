package com.open.therapyconnect.platform.sessionandlive.interfaces.rest;

import com.open.therapyconnect.platform.sessionandlive.application.commandservices.WeeklyScheduleCommandService;
import com.open.therapyconnect.platform.sessionandlive.application.queryservices.WeeklyScheduleQueryService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.DeleteWeeklyScheduleCommand;
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
@RequestMapping(value = "/api/v1/weekly-schedules", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Weekly Schedules", description = "Teacher weekly schedule management endpoints")
public class WeeklySchedulesController {

    private final WeeklyScheduleCommandService commandService;
    private final WeeklyScheduleQueryService queryService;

    public WeeklySchedulesController(WeeklyScheduleCommandService commandService,
                                      WeeklyScheduleQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Create a weekly schedule")
    public ResponseEntity<?> createWeeklySchedule(@RequestBody CreateWeeklyScheduleResource resource) {
        var command = CreateWeeklyScheduleCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = commandService.handle(command)
                .flatMap(id -> queryService.handle(new GetWeeklyScheduleByIdQuery(id))
                        .<Result<com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.WeeklySchedule, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("WeeklySchedule", id.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                WeeklyScheduleResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{scheduleId}")
    @Operation(summary = "Get weekly schedule by ID")
    public ResponseEntity<WeeklyScheduleResource> getWeeklyScheduleById(@PathVariable Long scheduleId) {
        var schedule = queryService.handle(new GetWeeklyScheduleByIdQuery(scheduleId));
        if (schedule.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(WeeklyScheduleResourceFromEntityAssembler.toResourceFromEntity(schedule.get()));
    }

    @GetMapping("/teacher/{teacherId}")
    @Operation(summary = "Get weekly schedules by teacher ID")
    public ResponseEntity<List<WeeklyScheduleResource>> getWeeklySchedulesByTeacherId(@PathVariable Long teacherId) {
        var schedules = queryService.handle(new GetWeeklySchedulesByTeacherIdQuery(teacherId));
        var resources = schedules.stream()
                .map(WeeklyScheduleResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{scheduleId}")
    @Operation(summary = "Update weekly schedule")
    public ResponseEntity<?> updateWeeklySchedule(@PathVariable Long scheduleId,
                                                   @RequestBody UpdateWeeklyScheduleResource resource) {
        var command = UpdateWeeklyScheduleCommandFromResourceAssembler.toCommandFromResource(scheduleId, resource);
        var result = commandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                WeeklyScheduleResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{scheduleId}")
    @Operation(summary = "Delete weekly schedule")
    public ResponseEntity<?> deleteWeeklySchedule(@PathVariable Long scheduleId) {
        var command = new DeleteWeeklyScheduleCommand(scheduleId);
        var result = commandService.handle(command)
                .map(id -> new MessageResource("Weekly schedule with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(result, message -> message, HttpStatus.OK);
    }
}
