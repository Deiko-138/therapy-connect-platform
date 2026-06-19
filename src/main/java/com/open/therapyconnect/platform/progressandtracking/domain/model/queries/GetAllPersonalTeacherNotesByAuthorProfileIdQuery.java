package com.open.therapyconnect.platform.progressandtracking.domain.model.queries;

/**
 * Query to retrieve all PERSONAL teacher notes for a given author profile.
 *
 * @param authorProfileId the profile id of the teacher author
 */
public record GetAllPersonalTeacherNotesByAuthorProfileIdQuery(Long authorProfileId) {
}
