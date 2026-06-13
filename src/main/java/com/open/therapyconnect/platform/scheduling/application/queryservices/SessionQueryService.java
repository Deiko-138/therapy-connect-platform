package com.open.therapyconnect.platform.scheduling.application.queryservices;

import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Session;
import com.open.therapyconnect.platform.scheduling.domain.model.queries.*;
import java.util.List;
import java.util.Optional;

/**
 * Application service contract for queries over the Session aggregate.
 */
public interface SessionQueryService {
    Optional<Session> handle(GetSessionByIdQuery query);
    List<Session> handle(GetAllSessionsQuery query);
}