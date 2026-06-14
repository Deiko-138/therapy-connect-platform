package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.UpdateSessionEventCommand;
import com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects.SessionEventType;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.UpdateSessionEventResource;

public class UpdateSessionEventCommandFromResourceAssembler {

    public static UpdateSessionEventCommand toCommandFromResource(Long eventId, UpdateSessionEventResource resource) {
        return new UpdateSessionEventCommand(
                eventId,
                resource.title(),
                resource.description(),
                resource.eventDate(),
                resource.startTime(),
                resource.endTime(),
                SessionEventType.valueOf(resource.eventType().toUpperCase())
        );
    }
}
