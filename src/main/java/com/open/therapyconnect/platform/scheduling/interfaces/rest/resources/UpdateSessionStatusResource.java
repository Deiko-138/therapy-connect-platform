package com.open.therapyconnect.platform.scheduling.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Update session status request resource.
 */
@Schema(name = "UpdateSessionStatusRequest", description = "Request payload for updating session status")
public record UpdateSessionStatusResource(String status) {
    public UpdateSessionStatusResource {
        if (status == null || status.isBlank())
            throw new IllegalArgumentException("Status is required");
    }
}