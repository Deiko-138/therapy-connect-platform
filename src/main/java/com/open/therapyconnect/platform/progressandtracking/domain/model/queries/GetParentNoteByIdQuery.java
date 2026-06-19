package com.open.therapyconnect.platform.progressandtracking.domain.model.queries;

/**
 * Query to retrieve a parent note by its id.
 *
 * @param noteId the id of the parent note
 */
public record GetParentNoteByIdQuery(Long noteId) {
}
