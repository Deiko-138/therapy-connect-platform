package com.open.therapyconnect.platform.scheduling.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Update reminder request resource.
 */
@Schema(name = "UpdateReminderRequest", description = "Request payload for updating a reminder")
public record UpdateReminderResource(
        String title,
        String reminderDate,
        String reminderTime
) {}