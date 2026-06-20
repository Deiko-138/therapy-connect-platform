package com.open.therapyconnect.platform.marketplace.interfaces.rest.transform;

import com.open.therapyconnect.platform.marketplace.domain.model.commands.CreateProductCommand;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.CreateProductResource;

public class CreateProductCommandFromResourceAssembler {
    public static CreateProductCommand toCommandFromResource(CreateProductResource resource) {
        return new CreateProductCommand(
                resource.productName(),
                resource.productCategory(),
                resource.productType(),
                resource.availabilityState(),
                resource.availabilityQuantity(),
                resource.recommendationState(),
                resource.priority(),
                resource.expirationDate(),
                resource.groupType(),
                resource.price()
        );
    }
}
