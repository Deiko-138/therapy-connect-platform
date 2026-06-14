package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionObservation;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.SessionObservationResource;

public class SessionObservationResourceFromEntityAssembler {

    public static SessionObservationResource toResourceFromEntity(SessionObservation entity) {
        return new SessionObservationResource(
                entity.getId(),
                entity.getSessionId(),
                entity.getStudentId(),
                entity.getTeacherId(),
                entity.getObservationText(),
                entity.getObservationDate(),
                entity.getProgressRating()
        );
    }
}
