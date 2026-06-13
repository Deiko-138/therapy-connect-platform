package com.open.therapyconnect.platform.scheduling.domain.model.commands;

/**
 * Command to delete a reminder.
 *
 * @param reminderId The ID of the reminder to delete.
 */
public record DeleteReminderCommand(Long reminderId) {
    public DeleteReminderCommand {
        if (reminderId == null || reminderId <= 0)
            throw new IllegalArgumentException("reminderId is required");
    }
}