package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to create a weekly schedule for a teacher.
 */
public record CreateWeeklyScheduleCommand(
        Long teacherId,
        String weekStartDate,
        String weekEndDate,
        Integer totalSessions,
        String notes
) {
    public CreateWeeklyScheduleCommand {
        if (teacherId == null)
            throw new IllegalArgumentException("teacherId cannot be null");
        if (weekStartDate == null || weekStartDate.isBlank())
            throw new IllegalArgumentException("weekStartDate cannot be null or blank");
        if (weekEndDate == null || weekEndDate.isBlank())
            throw new IllegalArgumentException("weekEndDate cannot be null or blank");
        if (totalSessions == null || totalSessions < 0)
            throw new IllegalArgumentException("totalSessions cannot be null or negative");
    }
}
