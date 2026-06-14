package com.open.therapyconnect.platform.sessionandlive.application.internal.queryservices;

import com.open.therapyconnect.platform.sessionandlive.application.queryservices.WeeklyScheduleQueryService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.WeeklySchedule;
import com.open.therapyconnect.platform.sessionandlive.domain.model.queries.*;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.WeeklyScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeeklyScheduleQueryServiceImpl implements WeeklyScheduleQueryService {

    private final WeeklyScheduleRepository scheduleRepository;

    public WeeklyScheduleQueryServiceImpl(WeeklyScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Optional<WeeklySchedule> handle(GetWeeklyScheduleByIdQuery query) {
        return scheduleRepository.findById(query.scheduleId());
    }

    @Override
    public List<WeeklySchedule> handle(GetWeeklySchedulesByTeacherIdQuery query) {
        return scheduleRepository.findByTeacherId(query.teacherId());
    }
}
