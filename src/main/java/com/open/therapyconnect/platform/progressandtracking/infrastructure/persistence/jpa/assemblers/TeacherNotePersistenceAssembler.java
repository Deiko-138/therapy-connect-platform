package com.open.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.progressandtracking.domain.model.aggregates.TeacherNote;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionDescription;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.NoteContent;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.NoteDate;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ProfileId;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.SessionId;
import com.open.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.entities.TeacherNotePersistenceEntity;

/**
 * Static assembler between TeacherNote domain and persistence representations.
 */
public final class TeacherNotePersistenceAssembler {

    private TeacherNotePersistenceAssembler() {
    }

    public static TeacherNote toDomainFromPersistence(TeacherNotePersistenceEntity entity) {
        if (entity == null) return null;

        var teacherNote = new TeacherNote();
        teacherNote.setId(entity.getId());
        teacherNote.setNoteDate(new NoteDate(entity.getNoteDate()));
        teacherNote.setContent(new NoteContent(entity.getContent()));
        teacherNote.setConditionType(entity.getConditionType());
        teacherNote.setConditionDescription(new ConditionDescription(entity.getConditionDescription()));
        teacherNote.setAuthorProfileId(new ProfileId(entity.getAuthorProfileId()));
        teacherNote.setSessionId(new SessionId(entity.getSessionId()));
        teacherNote.setTeacherNoteType(entity.getTeacherNoteType());

        return teacherNote;
    }

    public static TeacherNotePersistenceEntity toPersistenceFromDomain(TeacherNote teacherNote) {
        if (teacherNote == null) return null;

        var entity = new TeacherNotePersistenceEntity();
        if (teacherNote.getId() != null) {
            entity.setId(teacherNote.getId());
        }
        entity.setNoteDate(teacherNote.getNoteDate().value());
        entity.setContent(teacherNote.getContent().value());
        entity.setConditionType(teacherNote.getConditionType());
        entity.setConditionDescription(teacherNote.getConditionDescription().value());
        entity.setAuthorProfileId(teacherNote.getAuthorProfileId().id());
        entity.setSessionId(teacherNote.getSessionId().id());
        entity.setTeacherNoteType(teacherNote.getTeacherNoteType());

        return entity;
    }
}
