package com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.entities.ReminderPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository for reminder persistence entities.
 */
@Repository
public interface ReminderPersistenceRepository extends JpaRepository<ReminderPersistenceEntity, Long> {
}