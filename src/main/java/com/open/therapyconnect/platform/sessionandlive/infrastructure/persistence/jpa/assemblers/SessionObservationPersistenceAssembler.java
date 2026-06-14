package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionObservation;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities.SessionObservationPersistenceEntity;

public final class SessionObservationPersistenceAssembler {

    private SessionObservationPersistenceAssembler() {}

    public static SessionObservation toDomainFromPersistence(SessionObservationPersistenceEntity entity) {
        if (entity == null) return null;
        var observation = new SessionObservation();
        observation.setId(entity.getId());
        observation.setSessionId(entity.getSessionId());
        observation.setStudentId(entity.getStudentId());
        observation.setTeacherId(entity.getTeacherId());
        observation.setObservationText(entity.getObservationText());
        observation.setObservationDate(entity.getObservationDate());
        observation.setProgressRating(entity.getProgressRating());
        return observation;
    }

    public static SessionObservationPersistenceEntity toPersistenceFromDomain(SessionObservation observation) {
        if (observation == null) return null;
        var entity = new SessionObservationPersistenceEntity();
        if (observation.getId() != null) entity.setId(observation.getId());
        entity.setSessionId(observation.getSessionId());
        entity.setStudentId(observation.getStudentId());
        entity.setTeacherId(observation.getTeacherId());
        entity.setObservationText(observation.getObservationText());
        entity.setObservationDate(observation.getObservationDate());
        entity.setProgressRating(observation.getProgressRating());
        return entity;
    }
}
