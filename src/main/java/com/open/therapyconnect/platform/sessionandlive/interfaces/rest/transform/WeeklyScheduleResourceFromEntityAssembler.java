package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.WeeklySchedule;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.WeeklyScheduleResource;

public class WeeklyScheduleResourceFromEntityAssembler {

    public static WeeklyScheduleResource toResourceFromEntity(WeeklySchedule entity) {
        return new WeeklyScheduleResource(
                entity.getId(),
                entity.getTeacherId(),
                entity.getWeekStartDate(),
                entity.getWeekEndDate(),
                entity.getTotalSessions(),
                entity.getNotes(),
                entity.getPublished()
        );
    }
}
