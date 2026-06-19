package com.open.therapyconnect.platform.progressandtracking.interfaces.rest.resources;

import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionType;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.TeacherNoteType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Teacher note response resource.
 */
@Schema(
    name = "TeacherNoteResponse",
    description = "Teacher note information response"
)
public record TeacherNoteResource(
    @Schema(description = "Note unique identifier", example = "2")
    Long id,

    @Schema(description = "Date of the note", example = "2026-06-02T09:30:00")
    LocalDateTime noteDate,

    @Schema(description = "Note content", example = "Student participated actively in session")
    String content,

    @Schema(description = "Condition type", example = "ASPERGER")
    ConditionType conditionType,

    @Schema(description = "Condition description", example = "Improved social interaction")
    String conditionDescription,

    @Schema(description = "Author profile id (IAM reference)", example = "28")
    Long authorProfileId,

    @Schema(description = "Session id (Reservations BC reference)", example = "1001")
    Long sessionId,

    @Schema(description = "Teacher note type", example = "PERSONAL")
    TeacherNoteType teacherNoteType
) {
}
