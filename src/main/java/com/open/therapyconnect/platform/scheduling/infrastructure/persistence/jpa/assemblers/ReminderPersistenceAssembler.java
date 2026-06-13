package com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Reminder;
import com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.entities.ReminderPersistenceEntity;

/**
 * Static assembler between reminder domain and persistence representations.
 */
public final class ReminderPersistenceAssembler {

    private ReminderPersistenceAssembler() {}

    public static Reminder toDomainFromPersistence(ReminderPersistenceEntity entity) {
        if (entity == null) return null;
        var reminder = new Reminder();
        reminder.setId(entity.getId());
        reminder.setTitle(entity.getTitle());
        reminder.setReminderDate(entity.getReminderDate());
        reminder.setReminderTime(entity.getReminderTime());
        reminder.setReminderStatus(entity.getReminderStatus());
        reminder.setSessionId(entity.getSessionId());
        return reminder;
    }

    public static ReminderPersistenceEntity toPersistenceFromDomain(Reminder reminder) {
        if (reminder == null) return null;
        var entity = new ReminderPersistenceEntity();
        if (reminder.getId() != null) entity.setId(reminder.getId());
        entity.setTitle(reminder.getTitle());
        entity.setReminderDate(reminder.getReminderDate());
        entity.setReminderTime(reminder.getReminderTime());
        entity.setReminderStatus(reminder.getReminderStatus());
        entity.setSessionId(reminder.getSessionId());
        return entity;
    }
}