package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionNote;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.SessionNoteRepository;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.assemblers.SessionNotePersistenceAssembler;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.repositories.SessionNotePersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SessionNoteRepositoryImpl implements SessionNoteRepository {

    private final SessionNotePersistenceRepository persistenceRepository;

    public SessionNoteRepositoryImpl(SessionNotePersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<SessionNote> findById(Long id) {
        return persistenceRepository.findById(id)
                .map(SessionNotePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<SessionNote> findAll() {
        return persistenceRepository.findAll().stream()
                .map(SessionNotePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<SessionNote> findBySessionId(Long sessionId) {
        return persistenceRepository.findBySessionId(sessionId).stream()
                .map(SessionNotePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public SessionNote save(SessionNote note) {
        var saved = persistenceRepository.save(SessionNotePersistenceAssembler.toPersistenceFromDomain(note));
        return SessionNotePersistenceAssembler.toDomainFromPersistence(saved);
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
