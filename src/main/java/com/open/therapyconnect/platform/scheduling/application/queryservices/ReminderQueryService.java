package com.open.therapyconnect.platform.scheduling.application.queryservices;

import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Reminder;
import com.open.therapyconnect.platform.scheduling.domain.model.queries.*;
import java.util.List;
import java.util.Optional;

/**
 * Application service contract for queries over the Reminder aggregate.
 */
public interface ReminderQueryService {
    Optional<Reminder> handle(GetReminderByIdQuery query);
    List<Reminder> handle(GetAllRemindersQuery query);
}