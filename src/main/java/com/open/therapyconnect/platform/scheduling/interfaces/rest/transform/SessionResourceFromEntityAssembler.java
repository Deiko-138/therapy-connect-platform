package com.open.therapyconnect.platform.scheduling.interfaces.rest.transform;

import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Session;
import com.open.therapyconnect.platform.scheduling.interfaces.rest.resources.SessionResource;

/**
 * Assembler to convert Session domain entity to SessionResource.
 */
public class SessionResourceFromEntityAssembler {

    public static SessionResource toResourceFromEntity(Session session) {
        return new SessionResource(
                session.getId(),
                session.getTitle(),
                session.getSessionDate(),
                session.getStartTime(),
                session.getEndTime(),
                session.getSessionType(),
                session.getSessionStatus(),
                session.getTeacherId(),
                session.getStudentId()
        );
    }
}