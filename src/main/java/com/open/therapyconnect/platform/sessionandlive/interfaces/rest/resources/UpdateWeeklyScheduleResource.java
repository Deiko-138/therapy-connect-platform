package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record UpdateWeeklyScheduleResource(
        String weekStartDate,
        String weekEndDate,
        Integer totalSessions,
        String notes
) {}
