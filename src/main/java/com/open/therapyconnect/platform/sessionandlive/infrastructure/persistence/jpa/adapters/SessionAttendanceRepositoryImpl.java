package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionAttendance;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.SessionAttendanceRepository;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.assemblers.SessionAttendancePersistenceAssembler;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.repositories.SessionAttendancePersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SessionAttendanceRepositoryImpl implements SessionAttendanceRepository {

    private final SessionAttendancePersistenceRepository persistenceRepository;

    public SessionAttendanceRepositoryImpl(SessionAttendancePersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<SessionAttendance> findById(Long id) {
        return persistenceRepository.findById(id)
                .map(SessionAttendancePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<SessionAttendance> findAll() {
        return persistenceRepository.findAll().stream()
                .map(SessionAttendancePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<SessionAttendance> findBySessionId(Long sessionId) {
        return persistenceRepository.findBySessionId(sessionId).stream()
                .map(SessionAttendancePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<SessionAttendance> findByStudentId(Long studentId) {
        return persistenceRepository.findByStudentId(studentId).stream()
                .map(SessionAttendancePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public SessionAttendance save(SessionAttendance attendance) {
        var saved = persistenceRepository.save(SessionAttendancePersistenceAssembler.toPersistenceFromDomain(attendance));
        return SessionAttendancePersistenceAssembler.toDomainFromPersistence(saved);
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
