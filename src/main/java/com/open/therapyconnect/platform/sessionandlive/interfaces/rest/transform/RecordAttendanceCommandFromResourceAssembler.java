package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.RecordAttendanceCommand;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.RecordAttendanceResource;

public class RecordAttendanceCommandFromResourceAssembler {

    public static RecordAttendanceCommand toCommandFromResource(RecordAttendanceResource resource) {
        return new RecordAttendanceCommand(
                resource.sessionId(),
                resource.studentId(),
                resource.attendanceStatus(),
                resource.attendanceDate(),
                resource.remarks()
        );
    }
}
