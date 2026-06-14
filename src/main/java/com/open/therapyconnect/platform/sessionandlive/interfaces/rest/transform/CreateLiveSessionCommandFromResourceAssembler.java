package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.CreateLiveSessionCommand;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.CreateLiveSessionResource;

public class CreateLiveSessionCommandFromResourceAssembler {

    public static CreateLiveSessionCommand toCommandFromResource(CreateLiveSessionResource resource) {
        return new CreateLiveSessionCommand(
                resource.sessionId(),
                resource.teacherId(),
                resource.studentId(),
                resource.title(),
                resource.sessionDate(),
                resource.startTime(),
                resource.endTime(),
                resource.sessionMode(),
                resource.joinUrl(),
                resource.platform(),
                resource.maxParticipants()
        );
    }
}
