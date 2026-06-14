package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record SessionObservationResource(
        Long id,
        Long sessionId,
        Long studentId,
        Long teacherId,
        String observationText,
        String observationDate,
        Integer progressRating
) {}
