package com.open.therapyconnect.platform.scheduling.application.internal.commandservices;

import com.open.therapyconnect.platform.scheduling.application.commandservices.SessionCommandService;
import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Session;
import com.open.therapyconnect.platform.scheduling.domain.model.commands.*;
import com.open.therapyconnect.platform.scheduling.domain.repositories.SessionRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

/**
 * Application service that executes session commands.
 */
@Service
public class SessionCommandServiceImpl implements SessionCommandService {

    private final SessionRepository sessionRepository;

    public SessionCommandServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateSessionCommand command) {
        var session = new Session(command);
        try {
            session = sessionRepository.save(session);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-session", e.getMessage()));
        }
        return Result.success(session.getId());
    }

    @Override
    public Result<Session, ApplicationError> handle(UpdateSessionCommand command) {
        var result = sessionRepository.findById(command.sessionId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("Session", command.sessionId().toString()));
        var sessionToUpdate = result.get();
        try {
            var updatedSession = sessionRepository.save(
                    sessionToUpdate.updateInformation(
                            command.title(),
                            command.sessionDate(),
                            command.startTime(),
                            command.endTime(),
                            command.sessionType()
                    )
            );
            return Result.success(updatedSession);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-session", e.getMessage()));
        }
    }

    @Override
    public Result<Session, ApplicationError> handle(UpdateSessionStatusCommand command) {
        var result = sessionRepository.findById(command.sessionId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("Session", command.sessionId().toString()));
        var sessionToUpdate = result.get();
        try {
            var updatedSession = sessionRepository.save(sessionToUpdate.updateStatus(command.status()));
            return Result.success(updatedSession);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-session-status", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteSessionCommand command) {
        if (!sessionRepository.existsById(command.sessionId()))
            return Result.failure(ApplicationError.notFound("Session", command.sessionId().toString()));
        try {
            sessionRepository.deleteById(command.sessionId());
            return Result.success(command.sessionId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-session", e.getMessage()));
        }
    }
}