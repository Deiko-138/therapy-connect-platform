package com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Session;
import com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.entities.SessionPersistenceEntity;

/**
 * Static assembler between session domain and persistence representations.
 */
public final class SessionPersistenceAssembler {

    private SessionPersistenceAssembler() {}

    public static Session toDomainFromPersistence(SessionPersistenceEntity entity) {
        if (entity == null) return null;
        var session = new Session();
        session.setId(entity.getId());
        session.setTitle(entity.getTitle());
        session.setSessionDate(entity.getSessionDate());
        session.setStartTime(entity.getStartTime());
        session.setEndTime(entity.getEndTime());
        session.setSessionType(entity.getSessionType());
        session.setSessionStatus(entity.getSessionStatus());
        session.setTeacherId(entity.getTeacherId());
        session.setStudentId(entity.getStudentId());
        return session;
    }

    public static SessionPersistenceEntity toPersistenceFromDomain(Session session) {
        if (session == null) return null;
        var entity = new SessionPersistenceEntity();
        if (session.getId() != null) entity.setId(session.getId());
        entity.setTitle(session.getTitle());
        entity.setSessionDate(session.getSessionDate());
        entity.setStartTime(session.getStartTime());
        entity.setEndTime(session.getEndTime());
        entity.setSessionType(session.getSessionType());
        entity.setSessionStatus(session.getSessionStatus());
        entity.setTeacherId(session.getTeacherId());
        entity.setStudentId(session.getStudentId());
        return entity;
    }
}