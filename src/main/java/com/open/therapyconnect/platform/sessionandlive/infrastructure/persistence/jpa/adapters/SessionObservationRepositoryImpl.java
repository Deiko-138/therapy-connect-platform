package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionObservation;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.SessionObservationRepository;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.assemblers.SessionObservationPersistenceAssembler;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.repositories.SessionObservationPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SessionObservationRepositoryImpl implements SessionObservationRepository {

    private final SessionObservationPersistenceRepository persistenceRepository;

    public SessionObservationRepositoryImpl(SessionObservationPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<SessionObservation> findById(Long id) {
        return persistenceRepository.findById(id)
                .map(SessionObservationPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<SessionObservation> findAll() {
        return persistenceRepository.findAll().stream()
                .map(SessionObservationPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<SessionObservation> findByStudentId(Long studentId) {
        return persistenceRepository.findByStudentId(studentId).stream()
                .map(SessionObservationPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<SessionObservation> findBySessionId(Long sessionId) {
        return persistenceRepository.findBySessionId(sessionId).stream()
                .map(SessionObservationPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public SessionObservation save(SessionObservation observation) {
        var saved = persistenceRepository.save(SessionObservationPersistenceAssembler.toPersistenceFromDomain(observation));
        return SessionObservationPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsById(Long id) {
        return persistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        persistenceRepository.deleteById(id);
    }
}
