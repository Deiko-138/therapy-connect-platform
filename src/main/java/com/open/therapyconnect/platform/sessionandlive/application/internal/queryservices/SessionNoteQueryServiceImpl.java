package com.open.therapyconnect.platform.sessionandlive.application.internal.queryservices;

import com.open.therapyconnect.platform.sessionandlive.application.queryservices.SessionNoteQueryService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionNote;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.SessionNoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionNoteQueryServiceImpl implements SessionNoteQueryService {

    private final SessionNoteRepository noteRepository;

    public SessionNoteQueryServiceImpl(SessionNoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Optional<SessionNote> handle(GetSessionNoteByIdQuery query) {
        return noteRepository.findById(query.noteId());
    }

    @Override
    public List<SessionNote> handle(GetNotesBySessionIdQuery query) {
        return noteRepository.findBySessionId(query.sessionId());
    }
}
