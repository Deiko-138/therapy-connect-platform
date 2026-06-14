package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to delete a weekly schedule.
 */
public record DeleteWeeklyScheduleCommand(Long scheduleId) {
    public DeleteWeeklyScheduleCommand {
        if (scheduleId == null)
            throw new IllegalArgumentException("scheduleId cannot be null");
    }
}
