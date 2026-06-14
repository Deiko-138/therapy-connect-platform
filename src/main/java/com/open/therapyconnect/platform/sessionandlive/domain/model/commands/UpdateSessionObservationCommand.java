package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to update an existing session observation.
 */
public record UpdateSessionObservationCommand(
        Long observationId,
        String observationText,
        String observationDate,
        Integer progressRating
) {
    public UpdateSessionObservationCommand {
        if (observationId == null)
            throw new IllegalArgumentException("observationId cannot be null");
        if (observationText == null || observationText.isBlank())
            throw new IllegalArgumentException("observationText cannot be null or blank");
        if (progressRating != null && (progressRating < 0 || progressRating > 10))
            throw new IllegalArgumentException("progressRating must be between 0 and 10");
    }
}
