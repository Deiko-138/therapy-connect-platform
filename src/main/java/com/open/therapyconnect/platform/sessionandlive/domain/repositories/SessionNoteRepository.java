package com.open.therapyconnect.platform.sessionandlive.domain.repositories;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionNote;

import java.util.List;
import java.util.Optional;

public interface SessionNoteRepository {
    Optional<SessionNote> findById(Long id);
    List<SessionNote> findAll();
    List<SessionNote> findBySessionId(Long sessionId);
    SessionNote save(SessionNote note);
    boolean existsById(Long id);
    void deleteById(Long id);
}
