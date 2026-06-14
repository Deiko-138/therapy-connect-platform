package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.repositories;

import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities.WeeklySchedulePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeeklySchedulePersistenceRepository extends JpaRepository<WeeklySchedulePersistenceEntity, Long> {
    List<WeeklySchedulePersistenceEntity> findByTeacherId(Long teacherId);
}
