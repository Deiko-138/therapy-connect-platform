package com.open.therapyconnect.platform.scheduling.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Update session request resource.
 */
@Schema(name = "UpdateSessionRequest", description = "Request payload for updating a session")
public record UpdateSessionResource(
        String title,
        String sessionDate,
        String startTime,
        String endTime,
        String sessionType
) {}