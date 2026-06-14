package com.open.therapyconnect.platform.sessionandlive.application.internal.queryservices;

import com.open.therapyconnect.platform.sessionandlive.application.queryservices.SessionObservationQueryService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionObservation;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.SessionObservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionObservationQueryServiceImpl implements SessionObservationQueryService {

    private final SessionObservationRepository observationRepository;

    public SessionObservationQueryServiceImpl(SessionObservationRepository observationRepository) {
        this.observationRepository = observationRepository;
    }

    @Override
    public Optional<SessionObservation> handle(GetSessionObservationByIdQuery query) {
        return observationRepository.findById(query.observationId());
    }

    @Override
    public List<SessionObservation> handle(GetObservationsByStudentIdQuery query) {
        return observationRepository.findByStudentId(query.studentId());
    }

    @Override
    public List<SessionObservation> handle(GetObservationsBySessionIdQuery query) {
        return observationRepository.findBySessionId(query.sessionId());
    }
}
