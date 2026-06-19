package com.therapyconnect.platform.progressandtracking.interfaces.rest.resources;

import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * Request payload for updating a parent note.
 */
@Schema(
    name = "UpdateParentNoteRequest",
    description = "Request payload for updating an existing parent note"
)
public record UpdateParentNoteResource(
    @Schema(description = "Date of the note", example = "2026-06-01T10:00:00")
    LocalDateTime noteDate,

    @Schema(description = "Note content", example = "Good progress observed at home")
    String content,

    @Schema(description = "Condition type", example = "ADHD")
    ConditionType conditionType,

    @Schema(description = "Condition description", example = "Shows difficulty concentrating")
    String conditionDescription,

    @Schema(description = "Recommended next steps", example = "Practice reading for 20 minutes daily")
    String nextSteps
) {
    public UpdateParentNoteResource {
        if (content == null || content.isBlank()) throw new IllegalArgumentException("Content is required");
        if (conditionType == null) throw new IllegalArgumentException("Condition type is required");
        if (nextSteps == null || nextSteps.isBlank()) throw new IllegalArgumentException("Next steps is required");
        if (noteDate == null) noteDate = LocalDateTime.now();
        if (conditionDescription == null) conditionDescription = "";
    }
}
