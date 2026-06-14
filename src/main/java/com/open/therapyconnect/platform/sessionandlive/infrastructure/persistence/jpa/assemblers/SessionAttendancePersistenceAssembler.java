package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.assemblers;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionAttendance;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities.SessionAttendancePersistenceEntity;

public final class SessionAttendancePersistenceAssembler {

    private SessionAttendancePersistenceAssembler() {}

    public static SessionAttendance toDomainFromPersistence(SessionAttendancePersistenceEntity entity) {
        if (entity == null) return null;
        var attendance = new SessionAttendance();
        attendance.setId(entity.getId());
        attendance.setSessionId(entity.getSessionId());
        attendance.setStudentId(entity.getStudentId());
        attendance.setAttendanceStatus(entity.getAttendanceStatus());
        attendance.setAttendanceDate(entity.getAttendanceDate());
        attendance.setRemarks(entity.getRemarks());
        return attendance;
    }

    public static SessionAttendancePersistenceEntity toPersistenceFromDomain(SessionAttendance attendance) {
        if (attendance == null) return null;
        var entity = new SessionAttendancePersistenceEntity();
        if (attendance.getId() != null) entity.setId(attendance.getId());
        entity.setSessionId(attendance.getSessionId());
        entity.setStudentId(attendance.getStudentId());
        entity.setAttendanceStatus(attendance.getAttendanceStatus());
        entity.setAttendanceDate(attendance.getAttendanceDate());
        entity.setRemarks(attendance.getRemarks());
        return entity;
    }
}
