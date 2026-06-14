package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionAttendance;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.AttendanceResource;

public class AttendanceResourceFromEntityAssembler {

    public static AttendanceResource toResourceFromEntity(SessionAttendance entity) {
        return new AttendanceResource(
                entity.getId(),
                entity.getSessionId(),
                entity.getStudentId(),
                entity.getAttendanceStatus() != null ? entity.getAttendanceStatus().name() : null,
                entity.getAttendanceDate(),
                entity.getRemarks()
        );
    }
}
