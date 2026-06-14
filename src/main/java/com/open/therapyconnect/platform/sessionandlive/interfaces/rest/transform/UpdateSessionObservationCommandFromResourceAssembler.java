package com.open.therapyconnect.platform.sessionandlive.interfaces.rest.transform;

import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.UpdateSessionObservationCommand;
import com.open.therapyconnect.platform.sessionandlive.interfaces.rest.resources.UpdateSessionObservationResource;

public class UpdateSessionObservationCommandFromResourceAssembler {

    public static UpdateSessionObservationCommand toCommandFromResource(Long observationId, UpdateSessionObservationResource resource) {
        return new UpdateSessionObservationCommand(
                observationId,
                resource.observationText(),
                resource.observationDate(),
                resource.progressRating()
        );
    }
}
