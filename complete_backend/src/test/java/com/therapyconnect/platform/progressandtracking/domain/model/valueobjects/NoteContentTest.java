package com.therapyconnect.platform.progressandtracking.domain.model.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NoteContentTest {

    @Test
    void noteContentWithValidValueCreatesSuccessfully() {
        var content = new NoteContent("El estudiante mostró avances notables.");
        assertEquals("El estudiante mostró avances notables.", content.value());
    }

    @Test
    void noteContentWithNullThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new NoteContent(null));
    }

    @Test
    void noteContentWithBlankThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new NoteContent("   "));
    }
}
