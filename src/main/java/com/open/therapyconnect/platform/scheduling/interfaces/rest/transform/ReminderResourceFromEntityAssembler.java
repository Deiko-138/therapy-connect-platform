package com.open.therapyconnect.platform.scheduling.interfaces.rest.transform;

import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Reminder;
import com.open.therapyconnect.platform.scheduling.interfaces.rest.resources.ReminderResource;

/**
 * Assembler to convert Reminder domain entity to ReminderResource.
 */
public class ReminderResourceFromEntityAssembler {

    public static ReminderResource toResourceFromEntity(Reminder reminder) {
        return new ReminderResource(
                reminder.getId(),
                reminder.getTitle(),
                reminder.getReminderDate(),
                reminder.getReminderTime(),
                reminder.getReminderStatus(),
                reminder.getSessionId()
        );
    }
}