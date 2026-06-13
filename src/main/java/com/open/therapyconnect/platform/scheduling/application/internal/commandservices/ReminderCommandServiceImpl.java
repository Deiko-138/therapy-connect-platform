package com.open.therapyconnect.platform.scheduling.application.internal.commandservices;

import com.open.therapyconnect.platform.scheduling.application.commandservices.ReminderCommandService;
import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Reminder;
import com.open.therapyconnect.platform.scheduling.domain.model.commands.*;
import com.open.therapyconnect.platform.scheduling.domain.repositories.ReminderRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

/**
 * Application service that executes reminder commands.
 */
@Service
public class ReminderCommandServiceImpl implements ReminderCommandService {

    private final ReminderRepository reminderRepository;

    public ReminderCommandServiceImpl(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateReminderCommand command) {
        var reminder = new Reminder(command);
        try {
            reminder = reminderRepository.save(reminder);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-reminder", e.getMessage()));
        }
        return Result.success(reminder.getId());
    }

    @Override
    public Result<Reminder, ApplicationError> handle(UpdateReminderCommand command) {
        var result = reminderRepository.findById(command.reminderId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("Reminder", command.reminderId().toString()));
        var reminderToUpdate = result.get();
        try {
            var updatedReminder = reminderRepository.save(
                    reminderToUpdate.updateInformation(
                            command.title(),
                            command.reminderDate(),
                            command.reminderTime()
                    )
            );
            return Result.success(updatedReminder);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-reminder", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteReminderCommand command) {
        if (!reminderRepository.existsById(command.reminderId()))
            return Result.failure(ApplicationError.notFound("Reminder", command.reminderId().toString()));
        try {
            reminderRepository.deleteById(command.reminderId());
            return Result.success(command.reminderId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-reminder", e.getMessage()));
        }
    }
}