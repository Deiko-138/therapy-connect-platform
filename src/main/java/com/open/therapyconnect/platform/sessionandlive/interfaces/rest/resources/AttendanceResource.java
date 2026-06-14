package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record AttendanceResource(
        Long id,
        Long sessionId,
        Long studentId,
        String attendanceStatus,
        String attendanceDate,
        String remarks
) {}
