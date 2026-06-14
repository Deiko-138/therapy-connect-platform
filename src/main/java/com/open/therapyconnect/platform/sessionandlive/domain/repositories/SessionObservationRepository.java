package com.open.therapyconnect.platform.sessionandlive.domain.repositories;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionObservation;

import java.util.List;
import java.util.Optional;

public interface SessionObservationRepository {
    Optional<SessionObservation> findById(Long id);
    List<SessionObservation> findAll();
    List<SessionObservation> findByStudentId(Long studentId);
    List<SessionObservation> findBySessionId(Long sessionId);
    SessionObservation save(SessionObservation observation);
    boolean existsById(Long id);
    void deleteById(Long id);
}
