package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record SessionNoteResource(
        Long id,
        Long sessionId,
        Long authorId,
        String title,
        String content,
        String noteDate
) {}
