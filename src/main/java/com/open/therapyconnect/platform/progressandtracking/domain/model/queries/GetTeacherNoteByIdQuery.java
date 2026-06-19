package com.open.therapyconnect.platform.progressandtracking.domain.model.queries;

/**
 * Query to retrieve a teacher note by its id.
 *
 * @param noteId the id of the teacher note
 */
public record GetTeacherNoteByIdQuery(Long noteId) {
}
