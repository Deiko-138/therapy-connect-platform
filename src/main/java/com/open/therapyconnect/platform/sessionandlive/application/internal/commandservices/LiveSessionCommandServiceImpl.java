package com.open.therapyconnect.platform.sessionandlive.application.internal.commandservices;

import com.open.therapyconnect.platform.sessionandlive.application.commandservices.LiveSessionCommandService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.LiveSession;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.*;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.LiveSessionRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class LiveSessionCommandServiceImpl implements LiveSessionCommandService {

    private final LiveSessionRepository liveSessionRepository;

    public LiveSessionCommandServiceImpl(LiveSessionRepository liveSessionRepository) {
        this.liveSessionRepository = liveSessionRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateLiveSessionCommand command) {
        var liveSession = new LiveSession(command);
        try {
            liveSession = liveSessionRepository.save(liveSession);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-live-session", e.getMessage()));
        }
        return Result.success(liveSession.getId());
    }

    @Override
    public Result<LiveSession, ApplicationError> handle(UpdateLiveSessionCommand command) {
        var result = liveSessionRepository.findById(command.liveSessionId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("LiveSession", command.liveSessionId().toString()));
        var toUpdate = result.get();
        try {
            var updated = liveSessionRepository.save(
                    toUpdate.updateInformation(command.title(), command.sessionDate(),
                            command.startTime(), command.endTime(),
                            command.joinUrl(), command.platform(), command.maxParticipants()));
            return Result.success(updated);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-live-session", e.getMessage()));
        }
    }

    @Override
    public Result<LiveSession, ApplicationError> handle(UpdateLiveSessionStatusCommand command) {
        var result = liveSessionRepository.findById(command.liveSessionId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("LiveSession", command.liveSessionId().toString()));
        try {
            var updated = liveSessionRepository.save(result.get().updateStatus(command.status()));
            return Result.success(updated);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-live-session-status", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteLiveSessionCommand command) {
        if (!liveSessionRepository.existsById(command.liveSessionId()))
            return Result.failure(ApplicationError.notFound("LiveSession", command.liveSessionId().toString()));
        try {
            liveSessionRepository.deleteById(command.liveSessionId());
            return Result.success(command.liveSessionId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-live-session", e.getMessage()));
        }
    }
}
