package com.open.therapyconnect.platform.scheduling.application.commandservices;

import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Session;
import com.open.therapyconnect.platform.scheduling.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

/**
 * Application service contract for commands over the Session aggregate.
 */
public interface SessionCommandService {
    Result<Long, ApplicationError> handle(CreateSessionCommand command);
    Result<Session, ApplicationError> handle(UpdateSessionCommand command);
    Result<Session, ApplicationError> handle(UpdateSessionStatusCommand command);
    Result<Long, ApplicationError> handle(DeleteSessionCommand command);
}