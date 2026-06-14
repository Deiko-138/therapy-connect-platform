package com.open.therapyconnect.platform.sessionandlive.application.internal.commandservices;

import com.open.therapyconnect.platform.sessionandlive.application.commandservices.SessionObservationCommandService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionObservation;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.*;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.SessionObservationRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class SessionObservationCommandServiceImpl implements SessionObservationCommandService {

    private final SessionObservationRepository observationRepository;

    public SessionObservationCommandServiceImpl(SessionObservationRepository observationRepository) {
        this.observationRepository = observationRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateSessionObservationCommand command) {
        var observation = new SessionObservation(command);
        try {
            observation = observationRepository.save(observation);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-session-observation", e.getMessage()));
        }
        return Result.success(observation.getId());
    }

    @Override
    public Result<SessionObservation, ApplicationError> handle(UpdateSessionObservationCommand command) {
        var result = observationRepository.findById(command.observationId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("SessionObservation", command.observationId().toString()));
        try {
            var updated = observationRepository.save(
                    result.get().updateInformation(command.observationText(), command.observationDate(), command.progressRating()));
            return Result.success(updated);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-session-observation", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteSessionObservationCommand command) {
        if (!observationRepository.existsById(command.observationId()))
            return Result.failure(ApplicationError.notFound("SessionObservation", command.observationId().toString()));
        try {
            observationRepository.deleteById(command.observationId());
            return Result.success(command.observationId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-session-observation", e.getMessage()));
        }
    }
}
