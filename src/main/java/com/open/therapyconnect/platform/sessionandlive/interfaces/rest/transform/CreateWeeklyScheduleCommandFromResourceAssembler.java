package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.CreateWeeklyScheduleCommand;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.CreateWeeklyScheduleResource;

public class CreateWeeklyScheduleCommandFromResourceAssembler {

    public static CreateWeeklyScheduleCommand toCommandFromResource(CreateWeeklyScheduleResource resource) {
        return new CreateWeeklyScheduleCommand(
                resource.teacherId(),
                resource.weekStartDate(),
                resource.weekEndDate(),
                resource.totalSessions(),
                resource.notes(),
                resource.published()
        );
    }
}
