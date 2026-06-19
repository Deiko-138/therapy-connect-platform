package com.therapyconnect.platform.progressandtracking.interfaces.rest.transform;

import com.therapyconnect.platform.progressandtracking.domain.model.aggregates.ParentNote;
import com.therapyconnect.platform.progressandtracking.interfaces.rest.resources.ParentNoteResource;

/**
 * Assembler to convert a ParentNote entity to a ParentNoteResource.
 */
public class ParentNoteResourceFromEntityAssembler {

    /**
     * Converts a ParentNote entity to a ParentNoteResource.
     *
     * @param entity The {@link ParentNote} entity to convert.
     * @return The {@link ParentNoteResource} resource that results from the conversion.
     */
    public static ParentNoteResource toResourceFromEntity(ParentNote entity) {
        return new ParentNoteResource(
                entity.getId(),
                entity.getNoteDate().value(),
                entity.getContent().value(),
                entity.getConditionType(),
                entity.getConditionDescription().value(),
                entity.getAuthorProfileId().id(),
                entity.getNextSteps().value()
        );
    }
}
