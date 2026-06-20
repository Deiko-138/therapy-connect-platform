package com.open.therapyconnect.platform.marketplace.interfaces.rest.resources;

public record ProductResource(Long id, String productName, String productCategory, String productType,
                              String availabilityState, Number availabilityQuantity,String recommendationState,
                              String priority, String expirationDate, String groupType, Double price
) {
}
