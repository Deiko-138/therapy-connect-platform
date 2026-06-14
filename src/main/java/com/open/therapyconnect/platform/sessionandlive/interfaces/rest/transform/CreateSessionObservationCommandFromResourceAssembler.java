package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.CreateSessionObservationCommand;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.CreateSessionObservationResource;

public class CreateSessionObservationCommandFromResourceAssembler {

    public static CreateSessionObservationCommand toCommandFromResource(CreateSessionObservationResource resource) {
        return new CreateSessionObservationCommand(
                resource.sessionId(),
                resource.studentId(),
                resource.teacherId(),
                resource.observationText(),
                resource.observationDate(),
                resource.progressRating()
        );
    }
}
