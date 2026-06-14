package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record RecordAttendanceResource(
        Long sessionId,
        Long studentId,
        String attendanceStatus,
        String attendanceDate,
        String remarks
) {}
