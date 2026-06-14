package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to create a note for a session.
 */
public record CreateSessionNoteCommand(
        Long sessionId,
        Long authorId,
        String title,
        String content,
        String noteDate
) {
    public CreateSessionNoteCommand {
        if (sessionId == null)
            throw new IllegalArgumentException("sessionId cannot be null");
        if (authorId == null)
            throw new IllegalArgumentException("authorId cannot be null");
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title cannot be null or blank");
        if (content == null || content.isBlank())
            throw new IllegalArgumentException("content cannot be null or blank");
        if (noteDate == null || noteDate.isBlank())
            throw new IllegalArgumentException("noteDate cannot be null or blank");
    }
}
