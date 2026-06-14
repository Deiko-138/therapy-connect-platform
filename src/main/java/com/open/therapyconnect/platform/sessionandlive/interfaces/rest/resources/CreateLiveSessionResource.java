package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record CreateLiveSessionResource(
        Long sessionId,
        Long teacherId,
        Long studentId,
        String title,
        String sessionDate,
        String startTime,
        String endTime,
        String sessionMode,
        String joinUrl,
        String platform,
        Integer maxParticipants
) {}
