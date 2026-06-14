package com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.CreateSessionEventCommand;
import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.SessionEventType;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * Aggregate root representing a calendar event that may be linked to a Scheduling session.
 */
@Getter
public class SessionEvent extends AbstractDomainAggregateRoot<SessionEvent> {

    @Setter private Long id;
    @Setter private Long sessionId;
    @Setter private Long teacherId;
    @Setter private String title;
    @Setter private String description;
    @Setter private String eventDate;
    @Setter private String startTime;
    @Setter private String endTime;
    @Setter private SessionEventType eventType;

    public SessionEvent() {
        this.title = Strings.EMPTY;
        this.description = Strings.EMPTY;
        this.eventDate = Strings.EMPTY;
        this.startTime = Strings.EMPTY;
        this.endTime = Strings.EMPTY;
        this.eventType = SessionEventType.OTHER;
    }

    public SessionEvent(CreateSessionEventCommand command) {
        this.sessionId = command.sessionId();
        this.teacherId = command.teacherId();
        this.title = command.title();
        this.description = command.description() != null ? command.description() : Strings.EMPTY;
        this.eventDate = command.eventDate();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.eventType = SessionEventType.valueOf(command.eventType().toUpperCase());
    }

    public SessionEvent updateInformation(String title, String description, String eventDate,
                                           String startTime, String endTime, SessionEventType eventType) {
        this.title = title;
        this.description = description != null ? description : Strings.EMPTY;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventType = eventType;
        return this;
    }
}
