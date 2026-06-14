package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionEvent;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.SessionEventRepository;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.assemblers.SessionEventPersistenceAssembler;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.repositories.SessionEventPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SessionEventRepositoryImpl implements SessionEventRepository {

    private final SessionEventPersistenceRepository persistenceRepository;

    public SessionEventRepositoryImpl(SessionEventPersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<SessionEvent> findById(Long id) {
        return persistenceRepository.findById(id)
                .map(SessionEventPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<SessionEvent> findAll() {
        return persistenceRepository.findAll().stream()
                .map(SessionEventPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<SessionEvent> findByTeacherId(Long teacherId) {
        return persistenceRepository.findByTeacherId(teacherId).stream()
                .map(SessionEventPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<SessionEvent> findByEventDateBetween(String startDate, String endDate) {
        return persistenceRepository.findByEventDateBetween(startDate, endDate).stream()
                .map(SessionEventPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public SessionEvent save(SessionEvent event) {
        var saved = persistenceRepository.save(SessionEventPersistenceAssembler.toPersistenceFromDomain(event));
        return SessionEventPersistenceAssembler.toDomainFromPersistence(saved);
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
