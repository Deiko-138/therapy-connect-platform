package com.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.adapters;

import com.therapyconnect.platform.progressandtracking.domain.model.aggregates.TeacherNote;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.TeacherNoteType;
import com.therapyconnect.platform.progressandtracking.domain.repositories.TeacherNoteRepository;
import com.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.assemblers.TeacherNotePersistenceAssembler;
import com.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.repositories.TeacherNotePersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository adapter that bridges the TeacherNote domain repository port with Spring Data JPA.
 */
@Repository
public class TeacherNoteRepositoryImpl implements TeacherNoteRepository {

    private final TeacherNotePersistenceRepository teacherNotePersistenceRepository;

    public TeacherNoteRepositoryImpl(TeacherNotePersistenceRepository teacherNotePersistenceRepository) {
        this.teacherNotePersistenceRepository = teacherNotePersistenceRepository;
    }

    @Override
    public Optional<TeacherNote> findById(Long id) {
        return teacherNotePersistenceRepository.findById(id)
                .map(TeacherNotePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<TeacherNote> findAll() {
        return teacherNotePersistenceRepository.findAll().stream()
                .map(TeacherNotePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<TeacherNote> findAllByAuthorProfileId(Long authorProfileId) {
        return teacherNotePersistenceRepository.findAllByAuthorProfileId(authorProfileId).stream()
                .map(TeacherNotePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<TeacherNote> findAllByAuthorProfileIdAndTeacherNoteType(
            Long authorProfileId, TeacherNoteType teacherNoteType) {
        return teacherNotePersistenceRepository
                .findAllByAuthorProfileIdAndTeacherNoteType(authorProfileId, teacherNoteType).stream()
                .map(TeacherNotePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public TeacherNote save(TeacherNote teacherNote) {
        var saved = teacherNotePersistenceRepository.save(
                TeacherNotePersistenceAssembler.toPersistenceFromDomain(teacherNote));
        return TeacherNotePersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsById(Long id) {
        return teacherNotePersistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        teacherNotePersistenceRepository.deleteById(id);
    }
}
