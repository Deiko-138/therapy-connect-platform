package com.open.therapyconnect.platform.scheduling.domain.model.queries;

/** Query to get a session by ID. */
public record GetSessionByIdQuery(Long sessionId) {
    public GetSessionByIdQuery {
        if (sessionId == null || sessionId <= 0)
            throw new IllegalArgumentException("sessionId is required");
    }
}