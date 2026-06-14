package com.open.therapyconnect.platform.sessionandlive.application.internal.queryservices;

import com.open.therapyconnect.platform.sessionandlive.application.queryservices.SessionAttendanceQueryService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionAttendance;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.SessionAttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionAttendanceQueryServiceImpl implements SessionAttendanceQueryService {

    private final SessionAttendanceRepository attendanceRepository;

    public SessionAttendanceQueryServiceImpl(SessionAttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Optional<SessionAttendance> handle(GetAttendanceByIdQuery query) {
        return attendanceRepository.findById(query.attendanceId());
    }

    @Override
    public List<SessionAttendance> handle(GetAttendanceBySessionIdQuery query) {
        return attendanceRepository.findBySessionId(query.sessionId());
    }

    @Override
    public List<SessionAttendance> handle(GetAttendanceByStudentIdQuery query) {
        return attendanceRepository.findByStudentId(query.studentId());
    }
}
