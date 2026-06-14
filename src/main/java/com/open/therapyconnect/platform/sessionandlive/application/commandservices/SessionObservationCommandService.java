package com.open.therapyconnect.platform.sessionandlive.application.commandservices;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionObservation;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface SessionObservationCommandService {
    Result<Long, ApplicationError> handle(CreateSessionObservationCommand command);
    Result<SessionObservation, ApplicationError> handle(UpdateSessionObservationCommand command);
    Result<Long, ApplicationError> handle(DeleteSessionObservationCommand command);
}
