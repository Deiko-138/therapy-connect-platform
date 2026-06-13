package com.open.therapyconnect.platform.scheduling.domain.model.commands;

/**
 * Command to delete a session.
 *
 * @param sessionId The ID of the session to delete.
 */
public record DeleteSessionCommand(Long sessionId) {
    public DeleteSessionCommand {
        if (sessionId == null || sessionId <= 0)
            throw new IllegalArgumentException("sessionId is required");
    }
}