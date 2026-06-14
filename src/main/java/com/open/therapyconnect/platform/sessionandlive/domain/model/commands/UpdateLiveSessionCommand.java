package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to update an existing live session's information.
 */
public record UpdateLiveSessionCommand(
        Long liveSessionId,
        String title,
        String sessionDate,
        String startTime,
        String endTime,
        String joinUrl,
        String platform,
        Integer maxParticipants
) {
    public UpdateLiveSessionCommand {
        if (liveSessionId == null)
            throw new IllegalArgumentException("liveSessionId cannot be null");
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title cannot be null or blank");
        if (sessionDate == null || sessionDate.isBlank())
            throw new IllegalArgumentException("sessionDate cannot be null or blank");
    }
}
