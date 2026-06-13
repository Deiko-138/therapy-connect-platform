package com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.entities.SessionPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for session persistence entities.
 */
@Repository
public interface SessionPersistenceRepository extends JpaRepository<SessionPersistenceEntity, Long> {
}