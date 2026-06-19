package com.therapyconnect.platform.progressandtracking.domain.model.aggregates;

import com.therapyconnect.platform.progressandtracking.domain.model.commands.CreateParentNoteCommand;
import com.therapyconnect.platform.progressandtracking.domain.model.commands.UpdateParentNoteCommand;
import com.therapyconnect.platform.progressandtracking.domain.model.events.NoteUpdatedEvent;
import com.therapyconnect.platform.progressandtracking.domain.model.events.ParentNoteCreatedEvent;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ConditionDescription;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.NoteContent;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.NoteDate;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.NextSteps;
import com.therapyconnect.platform.progressandtracking.domain.model.valueobjects.ProfileId;
import lombok.Getter;
import lombok.Setter;

/**
 * ParentNote entity.
 *
 * <p>
 * Extends Note with {@code nextSteps}, specific to notes written by parents.
 * </p>
 */
@Getter
public class ParentNote extends Note {

    @Setter
    private NextSteps nextSteps;

    /**
     * Default constructor.
     */
    public ParentNote() {
        super();
        this.nextSteps = new NextSteps(".");
    }

    /**
     * Constructor using a CreateParentNoteCommand.
     *
     * @param command the command carrying the creation data
     */
    public ParentNote(CreateParentNoteCommand command) {
        this.setNoteDate(new NoteDate(command.noteDate()));
        this.setContent(new NoteContent(command.content()));
        this.setConditionType(command.conditionType());
        this.setConditionDescription(new ConditionDescription(command.conditionDescription()));
        this.setAuthorProfileId(new ProfileId(command.authorProfileId()));
        this.nextSteps = new NextSteps(command.nextSteps());
        this.registerDomainEvent(new ParentNoteCreatedEvent(this));
    }

    /**
     * Updates this parent note with the data from an UpdateParentNoteCommand.
     *
     * @param command the command carrying the updated data
     * @return this ParentNote instance (fluent)
     */
    public ParentNote updateInformation(UpdateParentNoteCommand command) {
        this.setNoteDate(new NoteDate(command.noteDate()));
        this.setContent(new NoteContent(command.content()));
        this.setConditionType(command.conditionType());
        this.setConditionDescription(new ConditionDescription(command.conditionDescription()));
        this.nextSteps = new NextSteps(command.nextSteps());
        this.registerDomainEvent(new NoteUpdatedEvent(this));
        return this;
    }
}
