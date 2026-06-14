package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to delete a session observation.
 */
public record DeleteSessionObservationCommand(Long observationId) {
    public DeleteSessionObservationCommand {
        if (observationId == null)
            throw new IllegalArgumentException("observationId cannot be null");
    }
}
