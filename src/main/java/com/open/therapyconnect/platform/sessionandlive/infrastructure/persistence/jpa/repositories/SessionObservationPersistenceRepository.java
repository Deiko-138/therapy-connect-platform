package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities.SessionObservationPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionObservationPersistenceRepository extends JpaRepository<SessionObservationPersistenceEntity, Long> {
    List<SessionObservationPersistenceEntity> findByStudentId(Long studentId);
    List<SessionObservationPersistenceEntity> findBySessionId(Long sessionId);
}
