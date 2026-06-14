package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to delete a live session.
 */
public record DeleteLiveSessionCommand(Long liveSessionId) {
    public DeleteLiveSessionCommand {
        if (liveSessionId == null)
            throw new IllegalArgumentException("liveSessionId cannot be null");
    }
}
