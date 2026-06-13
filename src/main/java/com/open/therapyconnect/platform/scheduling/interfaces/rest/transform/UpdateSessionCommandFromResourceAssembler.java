package com.open.therapyconnect.platform.scheduling.interfaces.rest.transform;

import com.open.therapyconnect.platform.scheduling.domain.model.commands.UpdateSessionCommand;
import com.open.therapyconnect.platform.scheduling.interfaces.rest.resources.UpdateSessionResource;

/**
 * Assembler to convert UpdateSessionResource to UpdateSessionCommand.
 */
public class UpdateSessionCommandFromResourceAssembler {

    public static UpdateSessionCommand toCommandFromResource(Long sessionId, UpdateSessionResource resource) {
        return new UpdateSessionCommand(
                sessionId,
                resource.title(),
                resource.sessionDate(),
                resource.startTime(),
                resource.endTime(),
                resource.sessionType()
        );
    }
}