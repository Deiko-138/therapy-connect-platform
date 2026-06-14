package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record WeeklyScheduleResource(
        Long id,
        Long teacherId,
        String weekStartDate,
        String weekEndDate,
        Integer totalSessions,
        String notes,
        Boolean published
) {}
