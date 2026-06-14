package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to create a calendar event optionally linked to a Scheduling session.
 */
public record CreateSessionEventCommand(
        Long sessionId,
        Long teacherId,
        String title,
        String description,
        String eventDate,
        String startTime,
        String endTime,
        String eventType
) {
    public CreateSessionEventCommand {
        if (teacherId == null)
            throw new IllegalArgumentException("teacherId cannot be null");
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title cannot be null or blank");
        if (eventDate == null || eventDate.isBlank())
            throw new IllegalArgumentException("eventDate cannot be null or blank");
        if (startTime == null || startTime.isBlank())
            throw new IllegalArgumentException("startTime cannot be null or blank");
        if (endTime == null || endTime.isBlank())
            throw new IllegalArgumentException("endTime cannot be null or blank");
        if (eventType == null || eventType.isBlank())
            throw new IllegalArgumentException("eventType cannot be null or blank");
    }
}
