package com.open.therapyconnect.platform.progressandtracking.application.internal.queryservices;

import com.open.therapyconnect.platform.progressandtracking.application.queryservices.ParentNoteQueryService;
import com.open.therapyconnect.platform.progressandtracking.domain.model.aggregates.ParentNote;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllParentNotesByAuthorProfileIdQuery;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetAllParentNotesQuery;
import com.open.therapyconnect.platform.progressandtracking.domain.model.queries.GetParentNoteByIdQuery;
import com.open.therapyconnect.platform.progressandtracking.domain.repositories.ParentNoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Application service that executes parent note queries.
 */
@Service
public class ParentNoteQueryServiceImpl implements ParentNoteQueryService {

    private final ParentNoteRepository parentNoteRepository;

    public ParentNoteQueryServiceImpl(ParentNoteRepository parentNoteRepository) {
        this.parentNoteRepository = parentNoteRepository;
    }

    @Override
    public Optional<ParentNote> handle(GetParentNoteByIdQuery query) {
        return parentNoteRepository.findById(query.noteId());
    }

    @Override
    public List<ParentNote> handle(GetAllParentNotesQuery query) {
        return parentNoteRepository.findAll();
    }

    @Override
    public List<ParentNote> handle(GetAllParentNotesByAuthorProfileIdQuery query) {
        return parentNoteRepository.findAllByAuthorProfileId(query.authorProfileId());
    }
}
