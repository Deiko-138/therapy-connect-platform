package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities.SessionNotePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionNotePersistenceRepository extends JpaRepository<SessionNotePersistenceEntity, Long> {
    List<SessionNotePersistenceEntity> findBySessionId(Long sessionId);
}
