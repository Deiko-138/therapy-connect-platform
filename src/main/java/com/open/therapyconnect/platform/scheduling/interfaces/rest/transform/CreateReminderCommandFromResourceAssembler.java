package com.open.therapyconnect.platform.scheduling.interfaces.rest.transform;

import com.open.therapyconnect.platform.scheduling.domain.model.commands.CreateReminderCommand;
import com.open.therapyconnect.platform.scheduling.interfaces.rest.resources.CreateReminderResource;

/**
 * Assembler to convert CreateReminderResource to CreateReminderCommand.
 */
public class CreateReminderCommandFromResourceAssembler {

    public static CreateReminderCommand toCommandFromResource(CreateReminderResource resource) {
        return new CreateReminderCommand(
                resource.title(),
                resource.reminderDate(),
                resource.reminderTime(),
                resource.sessionId()
        );
    }
}