package com.open.therapyconnect.platform.scheduling.domain.model.commands;

/**
 * Command to update a session status.
 *
 * @param sessionId The ID of the session.
 * @param status The new status.
 */
public record UpdateSessionStatusCommand(Long sessionId, String status) {
    public UpdateSessionStatusCommand {
        if (sessionId == null || sessionId <= 0)
            throw new IllegalArgumentException("sessionId is required");
        if (status == null || status.isBlank())
            throw new IllegalArgumentException("status cannot be null or blank");
    }
}