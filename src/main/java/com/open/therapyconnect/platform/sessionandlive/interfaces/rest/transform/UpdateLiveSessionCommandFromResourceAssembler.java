package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.UpdateLiveSessionCommand;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.UpdateLiveSessionResource;

public class UpdateLiveSessionCommandFromResourceAssembler {

    public static UpdateLiveSessionCommand toCommandFromResource(Long liveSessionId, UpdateLiveSessionResource resource) {
        return new UpdateLiveSessionCommand(
                liveSessionId,
                resource.title(),
                resource.sessionDate(),
                resource.startTime(),
                resource.endTime(),
                resource.joinUrl(),
                resource.platform(),
                resource.maxParticipants()
        );
    }
}
