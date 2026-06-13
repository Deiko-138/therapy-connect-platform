package com.open.therapyconnect.platform.scheduling.domain.model.commands;

/**
 * Command to update a reminder.
 *
 * @param reminderId The ID of the reminder to update.
 * @param title The new title.
 * @param reminderDate The new date.
 * @param reminderTime The new time.
 */
public record UpdateReminderCommand(
        Long reminderId,
        String title,
        String reminderDate,
        String reminderTime
) {
    public UpdateReminderCommand {
        if (reminderId == null || reminderId <= 0)
            throw new IllegalArgumentException("reminderId is required");
    }
}