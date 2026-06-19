package com.therapyconnect.platform.progressandtracking.domain.model.valueobjects;

/**
 * Value object representing the next steps recommended in a parent note.
 *
 * @param value The next steps text. It cannot be null or blank.
 */
public record NextSteps(String value) {
    public NextSteps {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Next steps cannot be null or blank");
        }
    }
}
