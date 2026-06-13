package com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Session;
import com.open.therapyconnect.platform.scheduling.domain.repositories.SessionRepository;
import com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.assemblers.SessionPersistenceAssembler;
import com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.repositories.SessionPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository adapter that bridges the session domain repository port with Spring Data JPA.
 */
@Repository
public class SessionRepositoryImpl implements SessionRepository {

    private final SessionPersistenceRepository sessionPersistenceRepository;

    public SessionRepositoryImpl(SessionPersistenceRepository sessionPersistenceRepository) {
        this.sessionPersistenceRepository = sessionPersistenceRepository;
    }

    @Override
    public Optional<Session> findById(Long id) {
        return sessionPersistenceRepository.findById(id)
                .map(SessionPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Session> findAll() {
        return sessionPersistenceRepository.findAll().stream()
                .map(SessionPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Session save(Session session) {
        var saved = sessionPersistenceRepository.save(
                SessionPersistenceAssembler.toPersistenceFromDomain(session));
        return SessionPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsById(Long id) {
        return sessionPersistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        sessionPersistenceRepository.deleteById(id);
    }
}