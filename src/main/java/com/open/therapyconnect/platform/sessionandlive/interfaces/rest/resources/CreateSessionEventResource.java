package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record CreateSessionEventResource(
        Long sessionId,
        Long teacherId,
        String title,
        String description,
        String eventDate,
        String startTime,
        String endTime,
        String eventType
) {}
