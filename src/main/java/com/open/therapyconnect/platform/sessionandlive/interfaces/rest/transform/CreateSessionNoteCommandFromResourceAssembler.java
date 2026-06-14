package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.CreateSessionNoteCommand;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.CreateSessionNoteResource;

public class CreateSessionNoteCommandFromResourceAssembler {

    public static CreateSessionNoteCommand toCommandFromResource(CreateSessionNoteResource resource) {
        return new CreateSessionNoteCommand(
                resource.sessionId(),
                resource.authorId(),
                resource.title(),
                resource.content(),
                resource.noteDate()
        );
    }
}
