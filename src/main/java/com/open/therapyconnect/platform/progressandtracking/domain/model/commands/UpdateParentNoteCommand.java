package com.open.therapyconnect.platform.progressandtracking.domain.model.commands;

import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionType;

import java.time.LocalDateTime;

/**
 * Command for updating a parent note.
 *
 * @param noteDate             the new date
 * @param content              the new content
 * @param conditionType        the new condition type
 * @param conditionDescription the new condition description
 * @param nextSteps            the new next steps
 */
public record UpdateParentNoteCommand(
        LocalDateTime noteDate,
        String content,
        ConditionType conditionType,
        String conditionDescription,
        String nextSteps) {
}
