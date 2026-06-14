package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.LiveSession;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities.LiveSessionPersistenceEntity;

public final class LiveSessionPersistenceAssembler {

    private LiveSessionPersistenceAssembler() {}

    public static LiveSession toDomainFromPersistence(LiveSessionPersistenceEntity entity) {
        if (entity == null) return null;
        var liveSession = new LiveSession();
        liveSession.setId(entity.getId());
        liveSession.setSessionId(entity.getSessionId());
        liveSession.setTeacherId(entity.getTeacherId());
        liveSession.setStudentId(entity.getStudentId());
        liveSession.setTitle(entity.getTitle());
        liveSession.setSessionDate(entity.getSessionDate());
        liveSession.setStartTime(entity.getStartTime());
        liveSession.setEndTime(entity.getEndTime());
        liveSession.setSessionMode(entity.getSessionMode());
        liveSession.setLiveSessionStatus(entity.getLiveSessionStatus());
        liveSession.setJoinUrl(entity.getJoinUrl());
        liveSession.setPlatform(entity.getPlatform());
        liveSession.setMaxParticipants(entity.getMaxParticipants());
        return liveSession;
    }

    public static LiveSessionPersistenceEntity toPersistenceFromDomain(LiveSession liveSession) {
        if (liveSession == null) return null;
        var entity = new LiveSessionPersistenceEntity();
        if (liveSession.getId() != null) entity.setId(liveSession.getId());
        entity.setSessionId(liveSession.getSessionId());
        entity.setTeacherId(liveSession.getTeacherId());
        entity.setStudentId(liveSession.getStudentId());
        entity.setTitle(liveSession.getTitle());
        entity.setSessionDate(liveSession.getSessionDate());
        entity.setStartTime(liveSession.getStartTime());
        entity.setEndTime(liveSession.getEndTime());
        entity.setSessionMode(liveSession.getSessionMode());
        entity.setLiveSessionStatus(liveSession.getLiveSessionStatus());
        entity.setJoinUrl(liveSession.getJoinUrl());
        entity.setPlatform(liveSession.getPlatform());
        entity.setMaxParticipants(liveSession.getMaxParticipants());
        return entity;
    }
}
