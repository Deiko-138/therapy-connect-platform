package com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects;

/**
 * Value object representing the content of a note.
 *
 * @param value The note content. It cannot be null or blank.
 */
public record NoteContent(String value) {
    public NoteContent {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Note content cannot be null or blank");
        }
    }
}
