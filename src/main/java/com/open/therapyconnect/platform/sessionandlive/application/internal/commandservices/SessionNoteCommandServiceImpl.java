package com.open.therapyconnect.platform.sessionandlive.application.internal.commandservices;

import com.open.therapyconnect.platform.sessionandlive.application.commandservices.SessionNoteCommandService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionNote;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.*;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.SessionNoteRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class SessionNoteCommandServiceImpl implements SessionNoteCommandService {

    private final SessionNoteRepository noteRepository;

    public SessionNoteCommandServiceImpl(SessionNoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateSessionNoteCommand command) {
        var note = new SessionNote(command);
        try {
            note = noteRepository.save(note);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-session-note", e.getMessage()));
        }
        return Result.success(note.getId());
    }

    @Override
    public Result<SessionNote, ApplicationError> handle(UpdateSessionNoteCommand command) {
        var result = noteRepository.findById(command.noteId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("SessionNote", command.noteId().toString()));
        try {
            var updated = noteRepository.save(
                    result.get().updateInformation(command.title(), command.content(), command.noteDate()));
            return Result.success(updated);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-session-note", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteSessionNoteCommand command) {
        if (!noteRepository.existsById(command.noteId()))
            return Result.failure(ApplicationError.notFound("SessionNote", command.noteId().toString()));
        try {
            noteRepository.deleteById(command.noteId());
            return Result.success(command.noteId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-session-note", e.getMessage()));
        }
    }
}
