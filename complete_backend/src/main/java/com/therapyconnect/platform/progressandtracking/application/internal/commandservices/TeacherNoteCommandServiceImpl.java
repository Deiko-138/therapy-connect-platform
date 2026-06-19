package com.therapyconnect.platform.progressandtracking.application.internal.commandservices;

import com.therapyconnect.platform.progressandtracking.application.commandservices.TeacherNoteCommandService;
import com.therapyconnect.platform.progressandtracking.domain.model.aggregates.TeacherNote;
import com.therapyconnect.platform.progressandtracking.domain.model.commands.CreateTeacherNoteCommand;
import com.therapyconnect.platform.progressandtracking.domain.model.commands.DeleteNoteCommand;
import com.therapyconnect.platform.progressandtracking.domain.model.commands.UpdateTeacherNoteCommand;
import com.therapyconnect.platform.progressandtracking.domain.repositories.TeacherNoteRepository;
import com.therapyconnect.platform.shared.application.result.ApplicationError;
import com.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

/**
 * Application service that executes teacher note commands.
 */
@Service
public class TeacherNoteCommandServiceImpl implements TeacherNoteCommandService {

    private final TeacherNoteRepository teacherNoteRepository;

    public TeacherNoteCommandServiceImpl(TeacherNoteRepository teacherNoteRepository) {
        this.teacherNoteRepository = teacherNoteRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateTeacherNoteCommand command) {
        try {
            var teacherNote = new TeacherNote(command);
            var saved = teacherNoteRepository.save(teacherNote);
            return Result.success(saved.getId());
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("TeacherNote", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-teacher-note", e.getMessage()));
        }
    }

    @Override
    public Result<TeacherNote, ApplicationError> handle(Long noteId, UpdateTeacherNoteCommand command) {
        if (!teacherNoteRepository.existsById(noteId)) {
            return Result.failure(ApplicationError.notFound("TeacherNote", noteId.toString()));
        }
        try {
            var result = teacherNoteRepository.findById(noteId);
            if (result.isEmpty()) {
                return Result.failure(ApplicationError.notFound("TeacherNote", noteId.toString()));
            }
            var teacherNoteToUpdate = result.get();
            var updatedNote = teacherNoteRepository.save(teacherNoteToUpdate.updateInformation(command));
            return Result.success(updatedNote);
        } catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError("TeacherNote", e.getMessage()));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-teacher-note", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteNoteCommand command) {
        if (!teacherNoteRepository.existsById(command.noteId())) {
            return Result.failure(ApplicationError.notFound("TeacherNote", command.noteId().toString()));
        }
        try {
            teacherNoteRepository.deleteById(command.noteId());
            return Result.success(command.noteId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-teacher-note", e.getMessage()));
        }
    }
}
