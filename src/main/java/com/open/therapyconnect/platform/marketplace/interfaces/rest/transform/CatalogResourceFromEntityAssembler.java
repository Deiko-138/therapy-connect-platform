package com.open.therapyconnect.platform.marketplace.interfaces.rest.transform;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.CatalogResource;

public class CatalogResourceFromEntityAssembler {
    public static CatalogResource toResourceFromEntity(Catalog entity) {
        return new CatalogResource(
                entity.getId(),
                entity.getCatalogName(),
                entity.getProductId().value(),
                entity.getCatalogState().name(),
                entity.getDateUpdated()
        );
    }
}
