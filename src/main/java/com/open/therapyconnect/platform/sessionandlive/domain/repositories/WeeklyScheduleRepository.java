package com.open.therapyconnect.platform.sessionandlive.domain.repositories;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.WeeklySchedule;

import java.util.List;
import java.util.Optional;

public interface WeeklyScheduleRepository {
    Optional<WeeklySchedule> findById(Long id);
    List<WeeklySchedule> findAll();
    List<WeeklySchedule> findByTeacherId(Long teacherId);
    WeeklySchedule save(WeeklySchedule schedule);
    boolean existsById(Long id);
    void deleteById(Long id);
}
