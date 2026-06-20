package com.open.therapyconnect.platform.marketplace.interfaces.rest.resources;

import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.CatalogStates;

public record CreateCatalogResource(String catalogName, Long productId, String catalogState,
                                    String dateUpdated) {
        public CreateCatalogResource {
                if (catalogName == null || catalogName.isBlank()) {
                        throw new IllegalArgumentException("catalogName cannot be null or blank");
                }
                if (productId == null || productId.intValue() < 0) {
                        throw new IllegalArgumentException("productId cannot be null or negative");
                }
                if (catalogState == null || catalogState.isBlank() || CatalogStates.valueOf(catalogName) == null) {
                        throw new IllegalArgumentException("catalogState cannot be null or blank");
                }
                if (dateUpdated == null || dateUpdated.isBlank()) {
                        throw new IllegalArgumentException("dateUpdated cannot be null or blank");
                }
        }
}
