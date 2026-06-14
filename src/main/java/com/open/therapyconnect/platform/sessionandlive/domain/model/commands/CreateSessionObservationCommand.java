package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to create a child progress or behavioral observation for a session.
 */
public record CreateSessionObservationCommand(
        Long sessionId,
        Long studentId,
        Long teacherId,
        String observationText,
        String observationDate,
        Integer progressRating
) {
    public CreateSessionObservationCommand {
        if (sessionId == null)
            throw new IllegalArgumentException("sessionId cannot be null");
        if (studentId == null)
            throw new IllegalArgumentException("studentId cannot be null");
        if (teacherId == null)
            throw new IllegalArgumentException("teacherId cannot be null");
        if (observationText == null || observationText.isBlank())
            throw new IllegalArgumentException("observationText cannot be null or blank");
        if (observationDate == null || observationDate.isBlank())
            throw new IllegalArgumentException("observationDate cannot be null or blank");
        if (progressRating != null && (progressRating < 0 || progressRating > 10))
            throw new IllegalArgumentException("progressRating must be between 0 and 10");
    }
}
