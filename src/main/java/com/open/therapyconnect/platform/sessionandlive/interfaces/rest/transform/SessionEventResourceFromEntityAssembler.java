package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionEvent;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.SessionEventResource;

public class SessionEventResourceFromEntityAssembler {

    public static SessionEventResource toResourceFromEntity(SessionEvent entity) {
        return new SessionEventResource(
                entity.getId(),
                entity.getSessionId(),
                entity.getTeacherId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getEventDate(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getEventType() != null ? entity.getEventType().name() : null
        );
    }
}
