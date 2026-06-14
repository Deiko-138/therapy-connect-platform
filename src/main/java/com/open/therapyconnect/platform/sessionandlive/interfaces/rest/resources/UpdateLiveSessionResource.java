package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record UpdateLiveSessionResource(
        String title,
        String sessionDate,
        String startTime,
        String endTime,
        String joinUrl,
        String platform,
        Integer maxParticipants
) {}
