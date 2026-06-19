package com.open.therapyconnect.platform.progressandtracking.domain.model.aggregates;

import com.open.therapyconnect.platform.progressandtracking.domain.model.events.NoteDeletedEvent;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionDescription;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionType;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.NoteContent;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.NoteDate;
import com.open.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ProfileId;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Note aggregate root.
 *
 * <p>
 * Represents a progress-and-tracking note in the TherapyConnect platform.
 * A note records a therapeutic observation with a date, content, condition type,
 * and a reference to the author profile (from IAM bounded context).
 * </p>
 */
@Getter
public class Note extends AbstractDomainAggregateRoot<Note> {

    @Setter
    private Long id;

    @Setter
    private NoteDate noteDate;

    @Setter
    private NoteContent content;

    @Setter
    private ConditionType conditionType;

    @Setter
    private ConditionDescription conditionDescription;

    @Setter
    private ProfileId authorProfileId;

    /**
     * Default constructor. Initializes a note with default values.
     */
    public Note() {
        this.noteDate = new NoteDate(LocalDateTime.now());
        this.content = new NoteContent(".");
        this.conditionType = ConditionType.OTHER;
        this.conditionDescription = new ConditionDescription("");
        this.authorProfileId = new ProfileId(1L);
    }

    /**
     * Registers a NoteDeletedEvent to be published when this aggregate is deleted.
     */
    public void markAsDeleted() {
        this.registerDomainEvent(new NoteDeletedEvent(this.id));
    }
}
