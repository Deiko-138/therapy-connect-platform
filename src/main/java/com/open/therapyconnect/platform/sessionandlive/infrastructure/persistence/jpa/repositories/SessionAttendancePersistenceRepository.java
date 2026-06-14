package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities.SessionAttendancePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionAttendancePersistenceRepository extends JpaRepository<SessionAttendancePersistenceEntity, Long> {
    List<SessionAttendancePersistenceEntity> findBySessionId(Long sessionId);
    List<SessionAttendancePersistenceEntity> findByStudentId(Long studentId);
}
