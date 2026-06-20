package com.open.therapyconnect.platform.marketplace.domain.model.queries;

public record GetProductByIdQuery(Long productId) {
    public GetProductByIdQuery {
        if (productId == null || productId < 0)
            throw new IllegalArgumentException("productId is required");
    }
}
