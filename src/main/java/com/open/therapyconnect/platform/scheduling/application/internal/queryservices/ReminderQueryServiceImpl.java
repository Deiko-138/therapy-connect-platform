package com.open.therapyconnect.platform.scheduling.application.internal.queryservices;

import com.open.therapyconnect.platform.scheduling.application.queryservices.ReminderQueryService;
import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Reminder;
import com.open.therapyconnect.platform.scheduling.domain.model.queries.*;
import com.open.therapyconnect.platform.scheduling.domain.repositories.ReminderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Application service that executes reminder queries.
 */
@Service
public class ReminderQueryServiceImpl implements ReminderQueryService {

    private final ReminderRepository reminderRepository;

    public ReminderQueryServiceImpl(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Optional<Reminder> handle(GetReminderByIdQuery query) {
        return reminderRepository.findById(query.reminderId());
    }

    @Override
    public List<Reminder> handle(GetAllRemindersQuery query) {
        return reminderRepository.findAll();
    }
}