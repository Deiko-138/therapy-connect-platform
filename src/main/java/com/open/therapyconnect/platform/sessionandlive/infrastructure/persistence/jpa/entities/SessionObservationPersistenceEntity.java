package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.entities;

import com.open.therapyconnect.platform.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "session_observations")
@Getter
@Setter
@NoArgsConstructor
public class SessionObservationPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(nullable = false)
    private Long sessionId;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private Long teacherId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String observationText;

    @Column(nullable = false)
    private String observationDate;

    private Integer progressRating;
}
