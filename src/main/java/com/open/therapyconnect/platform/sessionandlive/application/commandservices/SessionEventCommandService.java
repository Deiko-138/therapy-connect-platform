package com.open.therapyconnect.platform.sessionandlive.application.commandservices;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionEvent;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface SessionEventCommandService {
    Result<Long, ApplicationError> handle(CreateSessionEventCommand command);
    Result<SessionEvent, ApplicationError> handle(UpdateSessionEventCommand command);
    Result<Long, ApplicationError> handle(DeleteSessionEventCommand command);
}
