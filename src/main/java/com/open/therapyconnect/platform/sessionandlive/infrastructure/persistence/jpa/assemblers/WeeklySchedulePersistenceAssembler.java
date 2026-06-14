package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.WeeklySchedule;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities.WeeklySchedulePersistenceEntity;

public final class WeeklySchedulePersistenceAssembler {

    private WeeklySchedulePersistenceAssembler() {}

    public static WeeklySchedule toDomainFromPersistence(WeeklySchedulePersistenceEntity entity) {
        if (entity == null) return null;
        var schedule = new WeeklySchedule();
        schedule.setId(entity.getId());
        schedule.setTeacherId(entity.getTeacherId());
        schedule.setWeekStartDate(entity.getWeekStartDate());
        schedule.setWeekEndDate(entity.getWeekEndDate());
        schedule.setTotalSessions(entity.getTotalSessions());
        schedule.setNotes(entity.getNotes());
        schedule.setPublished(entity.getPublished());
        return schedule;
    }

    public static WeeklySchedulePersistenceEntity toPersistenceFromDomain(WeeklySchedule schedule) {
        if (schedule == null) return null;
        var entity = new WeeklySchedulePersistenceEntity();
        if (schedule.getId() != null) entity.setId(schedule.getId());
        entity.setTeacherId(schedule.getTeacherId());
        entity.setWeekStartDate(schedule.getWeekStartDate());
        entity.setWeekEndDate(schedule.getWeekEndDate());
        entity.setTotalSessions(schedule.getTotalSessions());
        entity.setNotes(schedule.getNotes());
        entity.setPublished(schedule.getPublished());
        return entity;
    }
}
