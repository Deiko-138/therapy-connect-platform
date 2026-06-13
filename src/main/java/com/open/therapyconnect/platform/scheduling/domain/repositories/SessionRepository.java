package com.open.therapyconnect.platform.scheduling.domain.repositories;

import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Session;
import java.util.List;
import java.util.Optional;

/** Session repository port. */
public interface SessionRepository {
    Optional<Session> findById(Long id);
    List<Session> findAll();
    Session save(Session session);
    boolean existsById(Long id);
    void deleteById(Long id);
}