package com.open.therapyconnect.platform.scheduling.domain.model.aggregates;

import com.open.therapyconnect.platform.scheduling.domain.model.commands.CreateReminderCommand;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * Reminder aggregate root.
 */
@Getter
public class Reminder extends AbstractDomainAggregateRoot<Reminder> {

    @Setter private Long id;
    @Setter private String title;
    @Setter private String reminderDate;
    @Setter private String reminderTime;
    @Setter private String reminderStatus;
    @Setter private Long sessionId;

    public Reminder() {
        this.title = Strings.EMPTY;
        this.reminderDate = Strings.EMPTY;
        this.reminderTime = Strings.EMPTY;
        this.reminderStatus = "pending";
    }

    public Reminder(CreateReminderCommand command) {
        this.title = command.title();
        this.reminderDate = command.reminderDate();
        this.reminderTime = command.reminderTime();
        this.reminderStatus = "pending";
        this.sessionId = command.sessionId();
    }

    public Reminder updateInformation(String title, String reminderDate, String reminderTime) {
        this.title = title;
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
        return this;
    }
}