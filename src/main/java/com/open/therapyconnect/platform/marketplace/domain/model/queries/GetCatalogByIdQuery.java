package com.open.therapyconnect.platform.marketplace.domain.model.queries;

public record GetCatalogByIdQuery(Long catalogId) {
    public GetCatalogByIdQuery {
        if (catalogId == null || catalogId < 0)
            throw new IllegalArgumentException("catalogId is required");
    }
}
