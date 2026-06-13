package com.open.therapyconnect.platform.scheduling.interfaces.rest;

import com.open.therapyconnect.platform.scheduling.application.commandservices.ReminderCommandService;
import com.open.therapyconnect.platform.scheduling.application.queryservices.ReminderQueryService;
import com.open.therapyconnect.platform.scheduling.domain.model.commands.DeleteReminderCommand;
import com.open.therapyconnect.platform.scheduling.domain.model.queries.GetAllRemindersQuery;
import com.open.therapyconnect.platform.scheduling.domain.model.queries.GetReminderByIdQuery;
import com.open.therapyconnect.platform.scheduling.interfaces.rest.resources.*;
import com.open.therapyconnect.platform.scheduling.interfaces.rest.transform.*;
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

/**
 * REST controller that exposes reminder endpoints.
 */
@RestController
@RequestMapping(value = "/api/v1/reminders", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Reminders", description = "Reminder management endpoints")
public class RemindersController {

    private final ReminderCommandService reminderCommandService;
    private final ReminderQueryService reminderQueryService;

    public RemindersController(ReminderCommandService reminderCommandService, ReminderQueryService reminderQueryService) {
        this.reminderCommandService = reminderCommandService;
        this.reminderQueryService = reminderQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new reminder")
    public ResponseEntity<?> createReminder(@RequestBody CreateReminderResource resource) {
        var command = CreateReminderCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = reminderCommandService.handle(command)
                .flatMap(reminderId -> reminderQueryService.handle(new GetReminderByIdQuery(reminderId))
                        .<Result<com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Reminder, ApplicationError>>
                                map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("Reminder", reminderId.toString()))));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ReminderResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{reminderId}")
    @Operation(summary = "Get reminder by ID")
    public ResponseEntity<ReminderResource> getReminderById(@PathVariable Long reminderId) {
        var query = new GetReminderByIdQuery(reminderId);
        var reminder = reminderQueryService.handle(query);
        if (reminder.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ReminderResourceFromEntityAssembler.toResourceFromEntity(reminder.get()));
    }

    @GetMapping
    @Operation(summary = "Get all reminders")
    public ResponseEntity<List<ReminderResource>> getAllReminders() {
        var reminders = reminderQueryService.handle(new GetAllRemindersQuery());
        var resources = reminders.stream()
                .map(ReminderResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{reminderId}")
    @Operation(summary = "Update reminder")
    public ResponseEntity<?> updateReminder(@PathVariable Long reminderId, @RequestBody UpdateReminderResource resource) {
        var command = UpdateReminderCommandFromResourceAssembler.toCommandFromResource(reminderId, resource);
        var result = reminderCommandService.handle(command);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ReminderResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{reminderId}")
    @Operation(summary = "Delete reminder")
    public ResponseEntity<?> deleteReminder(@PathVariable Long reminderId) {
        var command = new DeleteReminderCommand(reminderId);
        var result = reminderCommandService.handle(command)
                .map(id -> new MessageResource("Reminder with given id successfully deleted"));
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                message -> message,
                HttpStatus.OK
        );
    }
}