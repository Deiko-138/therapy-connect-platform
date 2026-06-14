package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to delete a session calendar event.
 */
public record DeleteSessionEventCommand(Long eventId) {
    public DeleteSessionEventCommand {
        if (eventId == null)
            throw new IllegalArgumentException("eventId cannot be null");
    }
}
