package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.LiveSessionStatus;

/**
 * Command to update the lifecycle status of a live session.
 */
public record UpdateLiveSessionStatusCommand(Long liveSessionId, LiveSessionStatus status) {
    public UpdateLiveSessionStatusCommand {
        if (liveSessionId == null)
            throw new IllegalArgumentException("liveSessionId cannot be null");
        if (status == null)
            throw new IllegalArgumentException("status cannot be null");
    }
}
