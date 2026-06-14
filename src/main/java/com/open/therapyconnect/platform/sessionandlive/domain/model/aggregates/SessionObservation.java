package com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.CreateSessionObservationCommand;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * Aggregate root recording a therapist's behavioral or progress observation about a child in a session.
 */
@Getter
public class SessionObservation extends AbstractDomainAggregateRoot<SessionObservation> {

    @Setter private Long id;
    @Setter private Long sessionId;
    @Setter private Long studentId;
    @Setter private Long teacherId;
    @Setter private String observationText;
    @Setter private String observationDate;
    @Setter private Integer progressRating;

    public SessionObservation() {
        this.observationText = Strings.EMPTY;
        this.observationDate = Strings.EMPTY;
        this.progressRating = 0;
    }

    public SessionObservation(CreateSessionObservationCommand command) {
        this.sessionId = command.sessionId();
        this.studentId = command.studentId();
        this.teacherId = command.teacherId();
        this.observationText = command.observationText();
        this.observationDate = command.observationDate();
        this.progressRating = command.progressRating();
    }

    public SessionObservation updateInformation(String observationText, String observationDate, Integer progressRating) {
        this.observationText = observationText;
        this.observationDate = observationDate;
        this.progressRating = progressRating;
        return this;
    }
}
