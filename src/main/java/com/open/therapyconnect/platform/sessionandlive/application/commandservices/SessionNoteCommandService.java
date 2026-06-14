package com.open.therapyconnect.platform.sessionandlive.application.commandservices;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionNote;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface SessionNoteCommandService {
    Result<Long, ApplicationError> handle(CreateSessionNoteCommand command);
    Result<SessionNote, ApplicationError> handle(UpdateSessionNoteCommand command);
    Result<Long, ApplicationError> handle(DeleteSessionNoteCommand command);
}
