package com.open.therapyconnect.platform.sessionandlive.application.queryservices;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.WeeklySchedule;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface WeeklyScheduleQueryService {
    Optional<WeeklySchedule> handle(GetWeeklyScheduleByIdQuery query);
    List<WeeklySchedule> handle(GetWeeklySchedulesByTeacherIdQuery query);
}
