package com.open.therapyconnect.platform.scheduling.domain.model.queries;

/** Query to get a reminder by ID. */
public record GetReminderByIdQuery(Long reminderId) {
    public GetReminderByIdQuery {
        if (reminderId == null || reminderId <= 0)
            throw new IllegalArgumentException("reminderId is required");
    }
}