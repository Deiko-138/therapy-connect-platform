package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.AttendanceStatus;

/**
 * Command to update an existing attendance record.
 */
public record UpdateAttendanceCommand(Long attendanceId, AttendanceStatus attendanceStatus, String remarks) {
    public UpdateAttendanceCommand {
        if (attendanceId == null)
            throw new IllegalArgumentException("attendanceId cannot be null");
        if (attendanceStatus == null)
            throw new IllegalArgumentException("attendanceStatus cannot be null");
    }
}
