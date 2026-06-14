package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources;

public record CreateSessionNoteResource(
        Long sessionId,
        Long authorId,
        String title,
        String content,
        String noteDate
) {}
