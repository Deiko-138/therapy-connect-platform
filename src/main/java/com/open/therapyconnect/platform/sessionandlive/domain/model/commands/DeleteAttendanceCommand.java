package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to delete an attendance record.
 */
public record DeleteAttendanceCommand(Long attendanceId) {
    public DeleteAttendanceCommand {
        if (attendanceId == null)
            throw new IllegalArgumentException("attendanceId cannot be null");
    }
}
