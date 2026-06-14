package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.CreateSessionEventCommand;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.CreateSessionEventResource;

public class CreateSessionEventCommandFromResourceAssembler {

    public static CreateSessionEventCommand toCommandFromResource(CreateSessionEventResource resource) {
        return new CreateSessionEventCommand(
                resource.sessionId(),
                resource.teacherId(),
                resource.title(),
                resource.description(),
                resource.eventDate(),
                resource.startTime(),
                resource.endTime(),
                resource.eventType()
        );
    }
}
