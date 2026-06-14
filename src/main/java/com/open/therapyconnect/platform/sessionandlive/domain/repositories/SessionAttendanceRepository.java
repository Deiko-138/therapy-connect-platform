package com.open.therapyconnect.platform.sessionandlive.domain.repositories;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionAttendance;

import java.util.List;
import java.util.Optional;

public interface SessionAttendanceRepository {
    Optional<SessionAttendance> findById(Long id);
    List<SessionAttendance> findAll();
    List<SessionAttendance> findBySessionId(Long sessionId);
    List<SessionAttendance> findByStudentId(Long studentId);
    SessionAttendance save(SessionAttendance attendance);
    boolean existsById(Long id);
    void deleteById(Long id);
}
