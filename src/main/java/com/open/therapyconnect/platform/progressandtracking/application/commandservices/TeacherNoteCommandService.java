package com.open.therapyconnect.platform.progressandtracking.application.commandservices;

import com.open.therapyconnect.platform.progressandtracking.domain.model.aggregates.TeacherNote;
import com.open.therapyconnect.platform.progressandtracking.domain.model.commands.CreateTeacherNoteCommand;
import com.open.therapyconnect.platform.progressandtracking.domain.model.commands.DeleteNoteCommand;
import com.open.therapyconnect.platform.progressandtracking.domain.model.commands.UpdateTeacherNoteCommand;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

/**
 * Application service contract for commands over the {@link TeacherNote} aggregate.
 */
public interface TeacherNoteCommandService {

    /**
     * Handles teacher note creation.
     *
     * @param command command containing initial teacher note data
     * @return created teacher note identifier or an application error
     * @see CreateTeacherNoteCommand
     */
    Result<Long, ApplicationError> handle(CreateTeacherNoteCommand command);

    /**
     * Handles teacher note update.
     *
     * @param noteId  the id of the note to update
     * @param command command containing updated teacher note data
     * @return updated teacher note aggregate or an application error
     * @see UpdateTeacherNoteCommand
     */
    Result<TeacherNote, ApplicationError> handle(Long noteId, UpdateTeacherNoteCommand command);

    /**
     * Handles teacher note deletion.
     *
     * @param command command containing target note id
     * @return deleted note identifier or an application error
     * @see DeleteNoteCommand
     */
    Result<Long, ApplicationError> handle(DeleteNoteCommand command);
}
