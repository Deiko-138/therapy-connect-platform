package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to record a student's attendance for a session.
 */
public record RecordAttendanceCommand(
        Long sessionId,
        Long studentId,
        String attendanceStatus,
        String attendanceDate,
        String remarks
) {
    public RecordAttendanceCommand {
        if (sessionId == null)
            throw new IllegalArgumentException("sessionId cannot be null");
        if (studentId == null)
            throw new IllegalArgumentException("studentId cannot be null");
        if (attendanceStatus == null || attendanceStatus.isBlank())
            throw new IllegalArgumentException("attendanceStatus cannot be null or blank");
        if (attendanceDate == null || attendanceDate.isBlank())
            throw new IllegalArgumentException("attendanceDate cannot be null or blank");
    }
}
