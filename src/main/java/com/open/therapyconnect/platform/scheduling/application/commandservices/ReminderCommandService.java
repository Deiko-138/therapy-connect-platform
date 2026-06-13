package com.open.therapyconnect.platform.scheduling.application.commandservices;

import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Reminder;
import com.open.therapyconnect.platform.scheduling.domain.model.commands.*;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;

/**
 * Application service contract for commands over the Reminder aggregate.
 */
public interface ReminderCommandService {
    Result<Long, ApplicationError> handle(CreateReminderCommand command);
    Result<Reminder, ApplicationError> handle(UpdateReminderCommand command);
    Result<Long, ApplicationError> handle(DeleteReminderCommand command);
}