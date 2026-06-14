package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "weekly_schedules")
@Getter
@Setter
@NoArgsConstructor
public class WeeklySchedulePersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private Long teacherId;

    @Column(nullable = false)
    private String weekStartDate;

    @Column(nullable = false)
    private String weekEndDate;

    @Column(nullable = false)
    private Integer totalSessions;

    private String notes;

    @Column(nullable = false)
    private Boolean published;
}
