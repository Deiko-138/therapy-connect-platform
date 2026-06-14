package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionNote;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities.SessionNotePersistenceEntity;

public final class SessionNotePersistenceAssembler {

    private SessionNotePersistenceAssembler() {}

    public static SessionNote toDomainFromPersistence(SessionNotePersistenceEntity entity) {
        if (entity == null) return null;
        var note = new SessionNote();
        note.setId(entity.getId());
        note.setSessionId(entity.getSessionId());
        note.setAuthorId(entity.getAuthorId());
        note.setTitle(entity.getTitle());
        note.setContent(entity.getContent());
        note.setNoteDate(entity.getNoteDate());
        return note;
    }

    public static SessionNotePersistenceEntity toPersistenceFromDomain(SessionNote note) {
        if (note == null) return null;
        var entity = new SessionNotePersistenceEntity();
        if (note.getId() != null) entity.setId(note.getId());
        entity.setSessionId(note.getSessionId());
        entity.setAuthorId(note.getAuthorId());
        entity.setTitle(note.getTitle());
        entity.setContent(note.getContent());
        entity.setNoteDate(note.getNoteDate());
        return entity;
    }
}
