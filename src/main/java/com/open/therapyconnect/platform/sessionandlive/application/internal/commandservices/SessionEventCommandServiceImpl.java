package com.open.therapyconnect.platform.sessionandlive.application.internal.commandservices;

import com.open.therapyconnect.platform.sessionandlive.application.commandservices.SessionEventCommandService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionEvent;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.*;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.SessionEventRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class SessionEventCommandServiceImpl implements SessionEventCommandService {

    private final SessionEventRepository eventRepository;

    public SessionEventCommandServiceImpl(SessionEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateSessionEventCommand command) {
        var event = new SessionEvent(command);
        try {
            event = eventRepository.save(event);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-session-event", e.getMessage()));
        }
        return Result.success(event.getId());
    }

    @Override
    public Result<SessionEvent, ApplicationError> handle(UpdateSessionEventCommand command) {
        var result = eventRepository.findById(command.eventId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("SessionEvent", command.eventId().toString()));
        try {
            var updated = eventRepository.save(
                    result.get().updateInformation(command.title(), command.description(),
                            command.eventDate(), command.startTime(), command.endTime(), command.eventType()));
            return Result.success(updated);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-session-event", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteSessionEventCommand command) {
        if (!eventRepository.existsById(command.eventId()))
            return Result.failure(ApplicationError.notFound("SessionEvent", command.eventId().toString()));
        try {
            eventRepository.deleteById(command.eventId());
            return Result.success(command.eventId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-session-event", e.getMessage()));
        }
    }
}
