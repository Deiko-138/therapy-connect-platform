package com.open.therapyconnect.platform.scheduling.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Create reminder request resource.
 */
@Schema(name = "CreateReminderRequest", description = "Request payload for creating a new reminder")
public record CreateReminderResource(
        String title,
        String reminderDate,
        String reminderTime,
        Long sessionId
) {
    public CreateReminderResource {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title is required");
        if (reminderDate == null || reminderDate.isBlank())
            throw new IllegalArgumentException("Reminder date is required");
        if (reminderTime == null || reminderTime.isBlank())
            throw new IllegalArgumentException("Reminder time is required");
    }
}