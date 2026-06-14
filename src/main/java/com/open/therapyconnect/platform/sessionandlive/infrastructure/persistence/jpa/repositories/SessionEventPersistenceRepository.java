package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities.SessionEventPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionEventPersistenceRepository extends JpaRepository<SessionEventPersistenceEntity, Long> {
    List<SessionEventPersistenceEntity> findByTeacherId(Long teacherId);
    List<SessionEventPersistenceEntity> findByEventDateBetween(String startDate, String endDate);
}
