package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionEvent;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities.SessionEventPersistenceEntity;

public final class SessionEventPersistenceAssembler {

    private SessionEventPersistenceAssembler() {}

    public static SessionEvent toDomainFromPersistence(SessionEventPersistenceEntity entity) {
        if (entity == null) return null;
        var event = new SessionEvent();
        event.setId(entity.getId());
        event.setSessionId(entity.getSessionId());
        event.setTeacherId(entity.getTeacherId());
        event.setTitle(entity.getTitle());
        event.setDescription(entity.getDescription());
        event.setEventDate(entity.getEventDate());
        event.setStartTime(entity.getStartTime());
        event.setEndTime(entity.getEndTime());
        event.setEventType(entity.getEventType());
        return event;
    }

    public static SessionEventPersistenceEntity toPersistenceFromDomain(SessionEvent event) {
        if (event == null) return null;
        var entity = new SessionEventPersistenceEntity();
        if (event.getId() != null) entity.setId(event.getId());
        entity.setSessionId(event.getSessionId());
        entity.setTeacherId(event.getTeacherId());
        entity.setTitle(event.getTitle());
        entity.setDescription(event.getDescription());
        entity.setEventDate(event.getEventDate());
        entity.setStartTime(event.getStartTime());
        entity.setEndTime(event.getEndTime());
        entity.setEventType(event.getEventType());
        return entity;
    }
}
