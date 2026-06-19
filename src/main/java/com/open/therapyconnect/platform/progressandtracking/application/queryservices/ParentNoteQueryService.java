package com.open.therapyconnect.platform.progressandtracking.application.queryservices;

import com.open.therapyconnect.platform.progressandtracking.domain.model.aggregates.ParentNote;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllParentNotesByAuthorProfileIdQuery;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllParentNotesQuery;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetParentNoteByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Application service contract for parent note read queries.
 */
public interface ParentNoteQueryService {

    /**
     * Handles retrieval of a parent note by id.
     *
     * @param query note-id query
     * @return matching parent note, if found
     * @see GetParentNoteByIdQuery
     */
    Optional<ParentNote> handle(GetParentNoteByIdQuery query);

    /**
     * Handles retrieval of all parent notes.
     *
     * @param query query marker
     * @return list of all parent notes
     * @see GetAllParentNotesQuery
     */
    List<ParentNote> handle(GetAllParentNotesQuery query);

    /**
     * Handles retrieval of all parent notes for a given author profile.
     *
     * @param query query containing the author profile id
     * @return list of parent notes for the given author
     * @see GetAllParentNotesByAuthorProfileIdQuery
     */
    List<ParentNote> handle(GetAllParentNotesByAuthorProfileIdQuery query);
}
