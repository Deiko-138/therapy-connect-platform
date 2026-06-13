package com.open.therapyconnect.platform.scheduling.application.internal.queryservices;

import com.open.therapyconnect.platform.scheduling.application.queryservices.SessionQueryService;
import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Session;
import com.open.therapyconnect.platform.scheduling.domain.model.queries.*;
import com.open.therapyconnect.platform.scheduling.domain.repositories.SessionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Application service that executes session queries.
 */
@Service
public class SessionQueryServiceImpl implements SessionQueryService {

    private final SessionRepository sessionRepository;

    public SessionQueryServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Optional<Session> handle(GetSessionByIdQuery query) {
        return sessionRepository.findById(query.sessionId());
    }

    @Override
    public List<Session> handle(GetAllSessionsQuery query) {
        return sessionRepository.findAll();
    }
}