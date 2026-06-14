package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record UpdateSessionObservationResource(
        String observationText,
        String observationDate,
        Integer progressRating
) {}
