package com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects;

/**
 * Value object representing the profile id of the author.
 *
 * <p>References a profile in the IAM bounded context. Must be a positive Long value.</p>
 *
 * @param id The profile id. It cannot be null or less than 1.
 */
public record ProfileId(Long id) {
    public ProfileId {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("Profile id cannot be null or less than 1");
        }
    }
}
