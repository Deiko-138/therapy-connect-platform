package com.open.therapyconnect.platform.marketplace.interfaces.rest.transform;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {
    public static ProductResource toResourceFromEntity(Product entity) {
        return new ProductResource(
                entity.getId(),
                entity.getProductName(),
                entity.getProductCategory(),
                entity.getProductType(),
                entity.getAvailabilityState().name(),
                entity.getAvailableQuantity(),
                entity.getRecommendationState().name(),
                entity.getPriority().name(),
                entity.getExpirationDate(),
                entity.getGroupType(),
                entity.getPrice()
        );
    }
}
