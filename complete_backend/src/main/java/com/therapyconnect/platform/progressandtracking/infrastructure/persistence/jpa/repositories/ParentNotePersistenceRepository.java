package com.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.repositories;

import com.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.entities.ParentNotePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data repository for parent note persistence entities.
 */
@Repository
public interface ParentNotePersistenceRepository extends JpaRepository<ParentNotePersistenceEntity, Long> {

    List<ParentNotePersistenceEntity> findAllByAuthorProfileId(Long authorProfileId);
}
