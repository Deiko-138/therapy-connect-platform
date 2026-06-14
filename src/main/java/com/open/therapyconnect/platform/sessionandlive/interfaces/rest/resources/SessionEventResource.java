package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record SessionEventResource(
        Long id,
        Long sessionId,
        Long teacherId,
        String title,
        String description,
        String eventDate,
        String startTime,
        String endTime,
        String eventType
) {}
