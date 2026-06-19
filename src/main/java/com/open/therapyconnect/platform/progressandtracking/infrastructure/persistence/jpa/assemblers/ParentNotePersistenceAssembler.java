package com.open.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.progressandtracking.domain.model.aggregates.ParentNote;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionDescription;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.NoteContent;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.NoteDate;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.NextSteps;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ProfileId;
import com.open.therapyconnect.platform.progressandtracking.infrastructure.persistence.jpa.entities.ParentNotePersistenceEntity;

/**
 * Static assembler between ParentNote domain and persistence representations.
 */
public final class ParentNotePersistenceAssembler {

    private ParentNotePersistenceAssembler() {
    }

    public static ParentNote toDomainFromPersistence(ParentNotePersistenceEntity entity) {
        if (entity == null) return null;

        var parentNote = new ParentNote();
        parentNote.setId(entity.getId());
        parentNote.setNoteDate(new NoteDate(entity.getNoteDate()));
        parentNote.setContent(new NoteContent(entity.getContent()));
        parentNote.setConditionType(entity.getConditionType());
        parentNote.setConditionDescription(new ConditionDescription(entity.getConditionDescription()));
        parentNote.setAuthorProfileId(new ProfileId(entity.getAuthorProfileId()));
        parentNote.setNextSteps(new NextSteps(entity.getNextSteps()));

        return parentNote;
    }

    public static ParentNotePersistenceEntity toPersistenceFromDomain(ParentNote parentNote) {
        if (parentNote == null) return null;

        var entity = new ParentNotePersistenceEntity();
        if (parentNote.getId() != null) {
            entity.setId(parentNote.getId());
        }
        entity.setNoteDate(parentNote.getNoteDate().value());
        entity.setContent(parentNote.getContent().value());
        entity.setConditionType(parentNote.getConditionType());
        entity.setConditionDescription(parentNote.getConditionDescription().value());
        entity.setAuthorProfileId(parentNote.getAuthorProfileId().id());
        entity.setNextSteps(parentNote.getNextSteps().value());

        return entity;
    }
}
