package com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.CreateSessionNoteCommand;
import com.open.therapyconnect.platform.shared.domain.model.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

/**
 * Aggregate root representing a note written by a therapist or teacher about a session.
 */
@Getter
public class SessionNote extends AbstractDomainAggregateRoot<SessionNote> {

    @Setter private Long id;
    @Setter private Long sessionId;
    @Setter private Long authorId;
    @Setter private String title;
    @Setter private String content;
    @Setter private String noteDate;

    public SessionNote() {
        this.title = Strings.EMPTY;
        this.content = Strings.EMPTY;
        this.noteDate = Strings.EMPTY;
    }

    public SessionNote(CreateSessionNoteCommand command) {
        this.sessionId = command.sessionId();
        this.authorId = command.authorId();
        this.title = command.title();
        this.content = command.content();
        this.noteDate = command.noteDate();
    }

    public SessionNote updateInformation(String title, String content, String noteDate) {
        this.title = title;
        this.content = content;
        this.noteDate = noteDate;
        return this;
    }
}
