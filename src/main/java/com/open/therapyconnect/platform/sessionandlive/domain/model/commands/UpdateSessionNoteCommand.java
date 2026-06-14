package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to update an existing session note.
 */
public record UpdateSessionNoteCommand(Long noteId, String title, String content, String noteDate) {
    public UpdateSessionNoteCommand {
        if (noteId == null)
            throw new IllegalArgumentException("noteId cannot be null");
        if (title == null || title.isBlank())
            throw new IllegalArgumentException("title cannot be null or blank");
        if (content == null || content.isBlank())
            throw new IllegalArgumentException("content cannot be null or blank");
    }
}
