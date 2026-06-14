package com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.CreateLiveSessionCommand;
import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.LiveSessionStatus;
import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.SessionMode;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * Aggregate root representing a live, private, or group session with real-time access metadata.
 * References a Scheduling Session by sessionId.
 */
@Getter
public class LiveSession extends AbstractDomainAggregateRoot<LiveSession> {

    @Setter private Long id;
    @Setter private Long sessionId;
    @Setter private Long teacherId;
    @Setter private Long studentId;
    @Setter private String title;
    @Setter private String sessionDate;
    @Setter private String startTime;
    @Setter private String endTime;
    @Setter private SessionMode sessionMode;
    @Setter private LiveSessionStatus liveSessionStatus;
    @Setter private String joinUrl;
    @Setter private String platform;
    @Setter private Integer maxParticipants;

    public LiveSession() {
        this.title = Strings.EMPTY;
        this.sessionDate = Strings.EMPTY;
        this.startTime = Strings.EMPTY;
        this.endTime = Strings.EMPTY;
        this.joinUrl = Strings.EMPTY;
        this.platform = Strings.EMPTY;
        this.sessionMode = SessionMode.LIVE;
        this.liveSessionStatus = LiveSessionStatus.SCHEDULED;
        this.maxParticipants = 1;
    }

    public LiveSession(CreateLiveSessionCommand command) {
        this.sessionId = command.sessionId();
        this.teacherId = command.teacherId();
        this.studentId = command.studentId();
        this.title = command.title();
        this.sessionDate = command.sessionDate();
        this.startTime = command.startTime();
        this.endTime = command.endTime();
        this.sessionMode = SessionMode.valueOf(command.sessionMode().toUpperCase());
        this.liveSessionStatus = LiveSessionStatus.SCHEDULED;
        this.joinUrl = command.joinUrl();
        this.platform = command.platform();
        this.maxParticipants = command.maxParticipants();
    }

    public LiveSession updateInformation(String title, String sessionDate, String startTime,
                                         String endTime, String joinUrl, String platform, Integer maxParticipants) {
        this.title = title;
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.joinUrl = joinUrl;
        this.platform = platform;
        this.maxParticipants = maxParticipants;
        return this;
    }

    public LiveSession updateStatus(LiveSessionStatus status) {
        this.liveSessionStatus = status;
        return this;
    }
}
