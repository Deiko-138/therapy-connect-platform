package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to update an existing weekly schedule.
 */
public record UpdateWeeklyScheduleCommand(
        Long scheduleId,
        String weekStartDate,
        String weekEndDate,
        Integer totalSessions,
        String notes
) {
    public UpdateWeeklyScheduleCommand {
        if (scheduleId == null)
            throw new IllegalArgumentException("scheduleId cannot be null");
        if (weekStartDate == null || weekStartDate.isBlank())
            throw new IllegalArgumentException("weekStartDate cannot be null or blank");
        if (weekEndDate == null || weekEndDate.isBlank())
            throw new IllegalArgumentException("weekEndDate cannot be null or blank");
    }
}
