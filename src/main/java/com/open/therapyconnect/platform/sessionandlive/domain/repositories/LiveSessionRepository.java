package com.open.therapyconnect.platform.sessionandlive.domain.repositories;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.LiveSession;

import java.util.List;
import java.util.Optional;

public interface LiveSessionRepository {
    Optional<LiveSession> findById(Long id);
    List<LiveSession> findAll();
    List<LiveSession> findByTeacherId(Long teacherId);
    List<LiveSession> findByStudentId(Long studentId);
    LiveSession save(LiveSession liveSession);
    boolean existsById(Long id);
    void deleteById(Long id);
}
