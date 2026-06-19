package com.open.therapyconnect.platform.progressandtracking.domain.model.queries;

/**
 * Query to retrieve all teacher notes (personal and institutional) for a given author profile.
 *
 * @param authorProfileId the profile id of the teacher author
 */
public record GetAllTeacherNotesByAuthorProfileIdQuery(Long authorProfileId) {
}
