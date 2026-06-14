package com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.CreateWeeklyScheduleCommand;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * Aggregate root representing a teacher's published weekly schedule.
 */
@Getter
public class WeeklySchedule extends AbstractDomainAggregateRoot<WeeklySchedule> {

    @Setter private Long id;
    @Setter private Long teacherId;
    @Setter private String weekStartDate;
    @Setter private String weekEndDate;
    @Setter private Integer totalSessions;
    @Setter private String notes;
    @Setter private Boolean published;

    public WeeklySchedule() {
        this.weekStartDate = Strings.EMPTY;
        this.weekEndDate = Strings.EMPTY;
        this.totalSessions = 0;
        this.notes = Strings.EMPTY;
        this.published = false;
    }

    public WeeklySchedule(CreateWeeklyScheduleCommand command) {
        this.teacherId = command.teacherId();
        this.weekStartDate = command.weekStartDate();
        this.weekEndDate = command.weekEndDate();
        this.totalSessions = command.totalSessions();
        this.notes = command.notes() != null ? command.notes() : Strings.EMPTY;
        this.published = false;
    }

    public WeeklySchedule updateInformation(String weekStartDate, String weekEndDate,
                                             Integer totalSessions, String notes) {
        this.weekStartDate = weekStartDate;
        this.weekEndDate = weekEndDate;
        this.totalSessions = totalSessions;
        this.notes = notes != null ? notes : Strings.EMPTY;
        return this;
    }

    public WeeklySchedule publish() {
        this.published = true;
        return this;
    }
}
