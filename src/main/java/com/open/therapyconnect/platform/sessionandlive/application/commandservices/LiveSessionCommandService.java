package com.open.therapyconnect.platform.sessionandlive.application.commandservices;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.LiveSession;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface LiveSessionCommandService {
    Result<Long, ApplicationError> handle(CreateLiveSessionCommand command);
    Result<LiveSession, ApplicationError> handle(UpdateLiveSessionCommand command);
    Result<LiveSession, ApplicationError> handle(UpdateLiveSessionStatusCommand command);
    Result<Long, ApplicationError> handle(DeleteLiveSessionCommand command);
}
