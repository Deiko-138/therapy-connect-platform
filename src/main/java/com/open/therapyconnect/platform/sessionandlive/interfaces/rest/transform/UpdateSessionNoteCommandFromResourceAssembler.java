package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.UpdateSessionNoteCommand;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.UpdateSessionNoteResource;

public class UpdateSessionNoteCommandFromResourceAssembler {

    public static UpdateSessionNoteCommand toCommandFromResource(Long noteId, UpdateSessionNoteResource resource) {
        return new UpdateSessionNoteCommand(
                noteId,
                resource.title(),
                resource.content(),
                resource.noteDate()
        );
    }
}
