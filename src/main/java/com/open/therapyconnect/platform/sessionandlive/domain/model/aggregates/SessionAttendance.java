package com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.RecordAttendanceCommand;
import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.AttendanceStatus;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * Aggregate root tracking the attendance of a student in a given session.
 */
@Getter
public class SessionAttendance extends AbstractDomainAggregateRoot<SessionAttendance> {

    @Setter private Long id;
    @Setter private Long sessionId;
    @Setter private Long studentId;
    @Setter private AttendanceStatus attendanceStatus;
    @Setter private String attendanceDate;
    @Setter private String remarks;

    public SessionAttendance() {
        this.attendanceDate = Strings.EMPTY;
        this.remarks = Strings.EMPTY;
        this.attendanceStatus = AttendanceStatus.ABSENT;
    }

    public SessionAttendance(RecordAttendanceCommand command) {
        this.sessionId = command.sessionId();
        this.studentId = command.studentId();
        this.attendanceStatus = AttendanceStatus.valueOf(command.attendanceStatus().toUpperCase());
        this.attendanceDate = command.attendanceDate();
        this.remarks = command.remarks() != null ? command.remarks() : Strings.EMPTY;
    }

    public SessionAttendance updateStatus(AttendanceStatus status, String remarks) {
        this.attendanceStatus = status;
        this.remarks = remarks != null ? remarks : Strings.EMPTY;
        return this;
    }
}
