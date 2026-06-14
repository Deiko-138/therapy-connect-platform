package com.open.therapyconnect.platform.sessionandlive.application.commandservices;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.WeeklySchedule;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

public interface WeeklyScheduleCommandService {
    Result<Long, ApplicationError> handle(CreateWeeklyScheduleCommand command);
    Result<WeeklySchedule, ApplicationError> handle(UpdateWeeklyScheduleCommand command);
    Result<Long, ApplicationError> handle(DeleteWeeklyScheduleCommand command);
}
