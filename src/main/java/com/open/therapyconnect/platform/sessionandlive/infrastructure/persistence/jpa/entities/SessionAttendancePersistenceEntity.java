package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.AttendanceStatus;
import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "session_attendances")
@Getter
@Setter
@NoArgsConstructor
public class SessionAttendancePersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private Long sessionId;

    @Column(nullable = false)
    private Long studentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttendanceStatus attendanceStatus;

    @Column(nullable = false)
    private String attendanceDate;

    private String remarks;
}
