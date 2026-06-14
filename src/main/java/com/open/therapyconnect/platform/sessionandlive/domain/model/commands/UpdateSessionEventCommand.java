package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.SessionEventType;

/**
 * Command to update an existing session calendar event.
 */
public record UpdateSessionEventCommand(
        Long eventId,
        String title,
        String description,
        String eventDate,
        String startTime,
        String endTime,
        SessionEventType eventType
) {
    public UpdateSessionEventCommand {
        if (eventId == null)
            throw new IllegalArgumentException("eventId cannot be null");
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title cannot be null or blank");
        if (eventDate == null || eventDate.isBlank())
            throw new IllegalArgumentException("eventDate cannot be null or blank");
    }
}
