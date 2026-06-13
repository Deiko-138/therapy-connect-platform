package com.open.therapyconnect.platform.scheduling.interfaces.rest.transform;

import com.open.therapyconnect.platform.scheduling.domain.model.commands.UpdateReminderCommand;
import com.open.therapyconnect.platform.scheduling.interfaces.rest.resources.UpdateReminderResource;

/**
 * Assembler to convert UpdateReminderResource to UpdateReminderCommand.
 */
public class UpdateReminderCommandFromResourceAssembler {

    public static UpdateReminderCommand toCommandFromResource(Long reminderId, UpdateReminderResource resource) {
        return new UpdateReminderCommand(
                reminderId,
                resource.title(),
                resource.reminderDate(),
                resource.reminderTime()
        );
    }
}