package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record UpdateSessionEventResource(
        String title,
        String description,
        String eventDate,
        String startTime,
        String endTime,
        String eventType
) {}
