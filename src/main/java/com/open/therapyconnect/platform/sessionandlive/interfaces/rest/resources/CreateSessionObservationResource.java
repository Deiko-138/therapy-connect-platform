package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record CreateSessionObservationResource(
        Long sessionId,
        Long studentId,
        Long teacherId,
        String observationText,
        String observationDate,
        Integer progressRating
) {}
