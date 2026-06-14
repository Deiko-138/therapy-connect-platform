package com.open.therapyconnect.platform.sessionandlive.application.commandservices;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionAttendance;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface SessionAttendanceCommandService {
    Result<Long, ApplicationError> handle(RecordAttendanceCommand command);
    Result<SessionAttendance, ApplicationError> handle(UpdateAttendanceCommand command);
    Result<Long, ApplicationError> handle(DeleteAttendanceCommand command);
}
