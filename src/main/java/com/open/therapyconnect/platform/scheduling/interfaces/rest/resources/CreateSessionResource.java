package com.open.therapyconnect.platform.scheduling.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;
/**
 * Create session request resource.
 */
@Schema(name = "CreateSessionRequest", description = "Request payload for creating a new session")
public record CreateSessionResource(
        String title,
        String sessionDate,
        String startTime,
        String endTime,
        String sessionType,
        Long teacherId,
        Long studentId
) {
    public CreateSessionResource {
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("Title is required");
        if (sessionDate == null || sessionDate.isBlank())
            throw new IllegalArgumentException("Session date is required");
        if (startTime == null || startTime.isBlank())
            throw new IllegalArgumentException("Start time is required");
        if (endTime == null || endTime.isBlank())
            throw new IllegalArgumentException("End time is required");
        if (sessionType == null || sessionType.isBlank())
            throw new IllegalArgumentException("Session type is required");
    }
}