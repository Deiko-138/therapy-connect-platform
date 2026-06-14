package com.open.therapyconnect.platform.sessionandlive.application.internal.commandservices;

import com.open.therapyconnect.platform.sessionandlive.application.commandservices.WeeklyScheduleCommandService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.WeeklySchedule;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.*;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.WeeklyScheduleRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class WeeklyScheduleCommandServiceImpl implements WeeklyScheduleCommandService {

    private final WeeklyScheduleRepository scheduleRepository;

    public WeeklyScheduleCommandServiceImpl(WeeklyScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(CreateWeeklyScheduleCommand command) {
        var schedule = new WeeklySchedule(command);
        try {
            schedule = scheduleRepository.save(schedule);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("create-weekly-schedule", e.getMessage()));
        }
        return Result.success(schedule.getId());
    }

    @Override
    public Result<WeeklySchedule, ApplicationError> handle(UpdateWeeklyScheduleCommand command) {
        var result = scheduleRepository.findById(command.scheduleId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("WeeklySchedule", command.scheduleId().toString()));
        try {
            var updated = scheduleRepository.save(
                    result.get().updateInformation(command.weekStartDate(), command.weekEndDate(),
                            command.totalSessions(), command.notes()));
            return Result.success(updated);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-weekly-schedule", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteWeeklyScheduleCommand command) {
        if (!scheduleRepository.existsById(command.scheduleId()))
            return Result.failure(ApplicationError.notFound("WeeklySchedule", command.scheduleId().toString()));
        try {
            scheduleRepository.deleteById(command.scheduleId());
            return Result.success(command.scheduleId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-weekly-schedule", e.getMessage()));
        }
    }
}
