package com.therapyconnect.platform.progressandtracking.interfaces.rest.transform;

import com.therapyconnect.platform.progressandtracking.domain.model.commands.UpdateParentNoteCommand;
import com.therapyconnect.platform.progressandtracking.interfaces.rest.resources.UpdateParentNoteResource;

/**
 * Assembler to convert an UpdateParentNoteResource to an UpdateParentNoteCommand.
 */
public class UpdateParentNoteCommandFromResourceAssembler {

    /**
     * Converts an UpdateParentNoteResource to an UpdateParentNoteCommand.
     *
     * @param resource The {@link UpdateParentNoteResource} resource to convert.
     * @return The {@link UpdateParentNoteCommand} command that results from the conversion.
     */
    public static UpdateParentNoteCommand toCommandFromResource(UpdateParentNoteResource resource) {
        return new UpdateParentNoteCommand(
                resource.noteDate(),
                resource.content(),
                resource.conditionType(),
                resource.conditionDescription(),
                resource.nextSteps()
        );
    }
}
