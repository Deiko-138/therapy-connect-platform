package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to create a live, private, or group session with real-time access data.
 */
public record CreateLiveSessionCommand(
        Long sessionId,
        Long teacherId,
        Long studentId,
        String title,
        String sessionDate,
        String startTime,
        String endTime,
        String sessionMode,
        String joinUrl,
        String platform,
        Integer maxParticipants
) {
    public CreateLiveSessionCommand {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title cannot be null or blank");
        if (sessionDate == null || sessionDate.isBlank())
            throw new IllegalArgumentException("sessionDate cannot be null or blank");
        if (startTime == null || startTime.isBlank())
            throw new IllegalArgumentException("startTime cannot be null or blank");
        if (endTime == null || endTime.isBlank())
            throw new IllegalArgumentException("endTime cannot be null or blank");
        if (sessionMode == null || sessionMode.isBlank())
            throw new IllegalArgumentException("sessionMode cannot be null or blank");
        if (teacherId == null)
            throw new IllegalArgumentException("teacherId cannot be null");
    }
}
