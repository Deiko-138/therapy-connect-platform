package com.open.therapyconnect.platform.scheduling.domain.model.commands;

/**
 * Command to create a reminder.
 *
 * @param title The title of the reminder.
 * @param reminderDate The date of the reminder.
 * @param reminderTime The time of the reminder.
 * @param sessionId The ID of the associated session.
 */
public record CreateReminderCommand(
        String title,
        String reminderDate,
        String reminderTime,
        Long sessionId
) {
    public CreateReminderCommand {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title cannot be null or blank");
        if (reminderDate == null || reminderDate.isBlank())
            throw new IllegalArgumentException("reminderDate cannot be null or blank");
        if (reminderTime == null || reminderTime.isBlank())
            throw new IllegalArgumentException("reminderTime cannot be null or blank");
    }
}