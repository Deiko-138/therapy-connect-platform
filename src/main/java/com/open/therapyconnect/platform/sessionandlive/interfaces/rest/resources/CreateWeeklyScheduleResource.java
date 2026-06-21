package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record CreateWeeklyScheduleResource(
        Long teacherId,
        String weekStartDate,
        String weekEndDate,
        Integer totalSessions,
        String notes,
        Boolean published
) {}
