package com.open.therapyconnect.platform.marketplace.interfaces.rest.resources;

public record CatalogResource(Long id, String catalogName, Long productId,
                              String catalogState, String dateUpdated
) {
}
