package com.open.therapyconnect.platform.scheduling.domain.model.commands;

/**
 * Command to create a session.
 *
 * @param title The title of the session.
 * @param sessionDate The date of the session.
 * @param startTime The start time of the session.
 * @param endTime The end time of the session.
 * @param sessionType The type of the session.
 * @param teacherId The ID of the teacher.
 * @param studentId The ID of the student.
 */
public record CreateSessionCommand(
        String title,
        String sessionDate,
        String startTime,
        String endTime,
        String sessionType,
        Long teacherId,
        Long studentId
) {
    public CreateSessionCommand {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title cannot be null or blank");
        if (sessionDate == null || sessionDate.isBlank())
            throw new IllegalArgumentException("sessionDate cannot be null or blank");
        if (startTime == null || startTime.isBlank())
            throw new IllegalArgumentException("startTime cannot be null or blank");
        if (endTime == null || endTime.isBlank())
            throw new IllegalArgumentException("endTime cannot be null or blank");
        if (sessionType == null || sessionType.isBlank())
            throw new IllegalArgumentException("sessionType cannot be null or blank");
    }
}