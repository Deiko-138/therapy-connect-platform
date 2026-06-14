package com.open.therapyconnect.platform.sessionandlive.application.queryservices;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionNote;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface SessionNoteQueryService {
    Optional<SessionNote> handle(GetSessionNoteByIdQuery query);
    List<SessionNote> handle(GetNotesBySessionIdQuery query);
}
