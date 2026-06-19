package com.open.therapyconnect.platform.progressandtracking.interfaces.rest.transform;

import com.open.therapyconnect.platform.progressandtracking.domain.model.commands.UpdateTeacherNoteCommand;
import com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources.UpdateTeacherNoteResource;

/**
 * Assembler to convert an UpdateTeacherNoteResource to an UpdateTeacherNoteCommand.
 */
public class UpdateTeacherNoteCommandFromResourceAssembler {

    /**
     * Converts an UpdateTeacherNoteResource to an UpdateTeacherNoteCommand.
     *
     * @param resource The {@link UpdateTeacherNoteResource} resource to convert.
     * @return The {@link UpdateTeacherNoteCommand} command that results from the conversion.
     */
    public static UpdateTeacherNoteCommand toCommandFromResource(UpdateTeacherNoteResource resource) {
        return new UpdateTeacherNoteCommand(
                resource.noteDate(),
                resource.content(),
                resource.conditionType(),
                resource.conditionDescription(),
                resource.sessionId(),
                resource.teacherNoteType()
        );
    }
}
