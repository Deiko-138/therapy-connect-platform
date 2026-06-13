package com.open.therapyconnect.platform.scheduling.domain.model.commands;

/**
 * Command to update a session.
 *
 * @param sessionId The ID of the session to update.
 * @param title The new title.
 * @param sessionDate The new date.
 * @param startTime The new start time.
 * @param endTime The new end time.
 * @param sessionType The new session type.
 */
public record UpdateSessionCommand(
        Long sessionId,
        String title,
        String sessionDate,
        String startTime,
        String endTime,
        String sessionType
) {
    public UpdateSessionCommand {
        if (sessionId == null || sessionId <= 0)
            throw new IllegalArgumentException("sessionId is required");
    }
}