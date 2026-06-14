package com.open.therapyconnect.platform.sessionandlive.application.queryservices;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionObservation;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface SessionObservationQueryService {
    Optional<SessionObservation> handle(GetSessionObservationByIdQuery query);
    List<SessionObservation> handle(GetObservationsByStudentIdQuery query);
    List<SessionObservation> handle(GetObservationsBySessionIdQuery query);
}
