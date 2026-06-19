package com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects;

/**
 * Value object representing a session id.
 *
 * <p>References a session in the Reservations bounded context. Must be a positive Long value.</p>
 *
 * @param id The session id. It cannot be null or less than 1.
 */
public record SessionId(Long id) {
    public SessionId {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("Session id cannot be null or less than 1");
        }
    }
}
