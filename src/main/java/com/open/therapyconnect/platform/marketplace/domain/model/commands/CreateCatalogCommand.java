package com.open.therapyconnect.platform.marketplace.domain.model.commands;

public record CreateCatalogCommand(String catalogName, Long productId, String catalogState, String dateUpdated) {
    public CreateCatalogCommand {
        if (catalogName == null)
            throw new IllegalArgumentException("catalogName is cannot be null or blank");
        if (productId == null)
            throw new IllegalArgumentException("Product Id cannot be null");
        if (catalogState == null)
            throw new IllegalArgumentException("Catalog State cannot be null");
        if (dateUpdated == null)
            throw new IllegalArgumentException("Date cannot be null or blank");
    }
}
