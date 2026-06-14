package com.open.therapyconnect.platform.sessionandlive.domain.repositories;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionEvent;

import java.util.List;
import java.util.Optional;

public interface SessionEventRepository {
    Optional<SessionEvent> findById(Long id);
    List<SessionEvent> findAll();
    List<SessionEvent> findByTeacherId(Long teacherId);
    List<SessionEvent> findByEventDateBetween(String startDate, String endDate);
    SessionEvent save(SessionEvent event);
    boolean existsById(Long id);
    void deleteById(Long id);
}
