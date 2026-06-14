package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionNote;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.SessionNoteResource;

public class SessionNoteResourceFromEntityAssembler {

    public static SessionNoteResource toResourceFromEntity(SessionNote entity) {
        return new SessionNoteResource(
                entity.getId(),
                entity.getSessionId(),
                entity.getAuthorId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getNoteDate()
        );
    }
}
