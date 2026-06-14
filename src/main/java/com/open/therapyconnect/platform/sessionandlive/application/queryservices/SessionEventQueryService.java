package com.open.therapyconnect.platform.sessionandlive.application.queryservices;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionEvent;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface SessionEventQueryService {
    Optional<SessionEvent> handle(GetSessionEventByIdQuery query);
    List<SessionEvent> handle(GetAllSessionEventsQuery query);
    List<SessionEvent> handle(GetEventsByTeacherIdQuery query);
    List<SessionEvent> handle(GetEventsByDateRangeQuery query);
}
