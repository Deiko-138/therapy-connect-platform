package com.open.therapyconnect.platform.scheduling.interfaces.rest.transform;

import com.open.therapyconnect.platform.scheduling.domain.model.commands.CreateSessionCommand;
import com.open.therapyconnect.platform.scheduling.interfaces.rest.resources.CreateSessionResource;

/**
 * Assembler to convert CreateSessionResource to CreateSessionCommand.
 */
public class CreateSessionCommandFromResourceAssembler {

    public static CreateSessionCommand toCommandFromResource(CreateSessionResource resource) {
        return new CreateSessionCommand(
                resource.title(),
                resource.sessionDate(),
                resource.startTime(),
                resource.endTime(),
                resource.sessionType(),
                resource.teacherId(),
                resource.studentId()
        );
    }
}