package com.open.therapyconnect.platform.sessionandlive.application.queryservices;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionAttendance;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface SessionAttendanceQueryService {
    Optional<SessionAttendance> handle(GetAttendanceByIdQuery query);
    List<SessionAttendance> handle(GetAttendanceBySessionIdQuery query);
    List<SessionAttendance> handle(GetAttendanceByStudentIdQuery query);
}
