package com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources;

import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionType;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.TeacherNoteType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Request payload for updating a teacher note.
 */
@Schema(
    name = "UpdateTeacherNoteRequest",
    description = "Request payload for updating an existing teacher note"
)
public record UpdateTeacherNoteResource(
    @Schema(description = "Date of the note", example = "2026-06-02T09:30:00")
    LocalDateTime noteDate,

    @Schema(description = "Note content", example = "Student participated actively in session")
    String content,

    @Schema(description = "Condition type", example = "ASPERGER")
    ConditionType conditionType,

    @Schema(description = "Condition description", example = "Improved social interaction")
    String conditionDescription,

    @Schema(description = "Session id (Reservations BC reference)", example = "1001")
    Long sessionId,

    @Schema(description = "Teacher note type (PERSONAL or INSTITUTIONAL)", example = "PERSONAL")
    TeacherNoteType teacherNoteType
) {
    public UpdateTeacherNoteResource {
        if (content == null || content.isBlank()) throw new IllegalArgumentException("Content is required");
        if (conditionType == null) throw new IllegalArgumentException("Condition type is required");
        if (sessionId == null || sessionId < 1) throw new IllegalArgumentException("Session id is required");
        if (teacherNoteType == null) throw new IllegalArgumentException("Teacher note type is required");
        if (noteDate == null) noteDate = LocalDateTime.now();
        if (conditionDescription == null) conditionDescription = "";
    }
}
