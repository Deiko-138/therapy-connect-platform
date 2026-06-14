package com.open.therapyconnect.platform.sessionandlive.domain.model.commands;

/**
 * Command to delete a session note.
 */
public record DeleteSessionNoteCommand(Long noteId) {
    public DeleteSessionNoteCommand {
        if (noteId == null)
            throw new IllegalArgumentException("noteId cannot be null");
    }
}
