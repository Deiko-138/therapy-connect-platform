package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.LiveSession;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.LiveSessionResource;

public class LiveSessionResourceFromEntityAssembler {

    public static LiveSessionResource toResourceFromEntity(LiveSession entity) {
        return new LiveSessionResource(
                entity.getId(),
                entity.getSessionId(),
                entity.getTeacherId(),
                entity.getStudentId(),
                entity.getTitle(),
                entity.getSessionDate(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getSessionMode() != null ? entity.getSessionMode().name() : null,
                entity.getLiveSessionStatus() != null ? entity.getLiveSessionStatus().name() : null,
                entity.getJoinUrl(),
                entity.getPlatform(),
                entity.getMaxParticipants()
        );
    }
}
