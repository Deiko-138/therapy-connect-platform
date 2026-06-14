package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record LiveSessionResource(
        Long id,
        Long sessionId,
        Long teacherId,
        Long studentId,
        String title,
        String sessionDate,
        String startTime,
        String endTime,
        String sessionMode,
        String liveSessionStatus,
        String joinUrl,
        String platform,
        Integer maxParticipants
) {}
