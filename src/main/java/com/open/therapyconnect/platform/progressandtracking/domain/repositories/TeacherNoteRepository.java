package com.open.therapyconnect.platform.progressandtracking.domain.repositories;

import com.open.therapyconnect.platform.progressandtracking.domain.model.aggregates.TeacherNote;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.TeacherNoteType;

import java.util.List;
import java.util.Optional;

/**
 * Progress-and-tracking teacher note repository port.
 */
public interface TeacherNoteRepository {

    Optional<TeacherNote> findById(Long id);

    List<TeacherNote> findAll();

    List<TeacherNote> findAllByAuthorProfileId(Long authorProfileId);

    List<TeacherNote> findAllByAuthorProfileIdAndTeacherNoteType(Long authorProfileId, TeacherNoteType teacherNoteType);

    TeacherNote save(TeacherNote teacherNote);

    boolean existsById(Long id);

    void deleteById(Long id);
}
