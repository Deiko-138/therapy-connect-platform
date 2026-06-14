package com.open.therapyconnect.platform.sessionandlive.application.internal.queryservices;

import com.open.therapyconnect.platform.sessionandlive.application.queryservices.SessionEventQueryService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionEvent;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.SessionEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionEventQueryServiceImpl implements SessionEventQueryService {

    private final SessionEventRepository eventRepository;

    public SessionEventQueryServiceImpl(SessionEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<SessionEvent> handle(GetSessionEventByIdQuery query) {
        return eventRepository.findById(query.eventId());
    }

    @Override
    public List<SessionEvent> handle(GetAllSessionEventsQuery query) {
        return eventRepository.findAll();
    }

    @Override
    public List<SessionEvent> handle(GetEventsByTeacherIdQuery query) {
        return eventRepository.findByTeacherId(query.teacherId());
    }

    @Override
    public List<SessionEvent> handle(GetEventsByDateRangeQuery query) {
        return eventRepository.findByEventDateBetween(query.startDate(), query.endDate());
    }
}
