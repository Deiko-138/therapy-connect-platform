package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.LiveSessionStatus;
import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.SessionMode;
import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "live_sessions")
@Getter
@Setter
@NoArgsConstructor
public class LiveSessionPersistenceEntity extends AuditableAbstractPersistenceEntity {

    private Long sessionId;

    private Long teacherId;

    private Long studentId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String sessionDate;

    @Column(nullable = false)
    private String startTime;

    @Column(nullable = false)
    private String endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SessionMode sessionMode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LiveSessionStatus liveSessionStatus;

    private String joinUrl;

    private String platform;

    private Integer maxParticipants;
}
