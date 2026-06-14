package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.UpdateWeeklyScheduleCommand;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.UpdateWeeklyScheduleResource;

public class UpdateWeeklyScheduleCommandFromResourceAssembler {

    public static UpdateWeeklyScheduleCommand toCommandFromResource(Long scheduleId, UpdateWeeklyScheduleResource resource) {
        return new UpdateWeeklyScheduleCommand(
                scheduleId,
                resource.weekStartDate(),
                resource.weekEndDate(),
                resource.totalSessions(),
                resource.notes()
        );
    }
}
