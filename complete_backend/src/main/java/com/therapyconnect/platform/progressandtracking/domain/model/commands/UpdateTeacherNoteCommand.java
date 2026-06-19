package com.therapyconnect.platform.progressandtracking.domain.model.commands;

import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionType;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.TeacherNoteType;

import java.time.LocalDateTime;

/**
 * Command for updating a teacher note.
 *
 * @param noteDate             the new date
 * @param content              the new content
 * @param conditionType        the new condition type
 * @param conditionDescription the new condition description
 * @param sessionId            the new session id
 * @param teacherNoteType      the new note type
 */
public record UpdateTeacherNoteCommand(
        LocalDateTime noteDate,
        String content,
        ConditionType conditionType,
        String conditionDescription,
        Long sessionId,
        TeacherNoteType teacherNoteType) {
}
