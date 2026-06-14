package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.SessionEventType;
import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "session_events")
@Getter
@Setter
@NoArgsConstructor
public class SessionEventPersistenceEntity extends AuditableAbstractPersistenceEntity {

    private Long sessionId;

    @Column(nullable = false)
    private Long teacherId;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String eventDate;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionEventType eventType;
}
