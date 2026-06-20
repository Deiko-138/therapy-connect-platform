package com.open.therapyconnect.platform.marketplace.interfaces.rest.resources;

import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.AvailabilityStates;
import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.Priority;
import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.RecommendationStates;

public record CreateProductResource(String productName, String productCategory, String productType,
        String availabilityState, Number availabilityQuantity, String recommendationState, String priority,
        String expirationDate, String groupType, Double price) {
        public CreateProductResource {
                if (productName == null || productName.isBlank()) {
                        throw new IllegalArgumentException("productName cannot be null or blank");
                }
                if (productCategory == null || productCategory.isBlank()) {
                        throw new IllegalArgumentException("productCategory cannot be null or blank");
                }
                if (productType == null || productType.isBlank()) {
                        throw new IllegalArgumentException("productType cannot be null or blank");
                }
                if (availabilityState == null || availabilityState.isBlank() || AvailabilityStates.valueOf(availabilityState) == null) {
                        throw new IllegalArgumentException("availabilityState cannot be null or blank");
                }
                if (availabilityQuantity == null || availabilityQuantity.intValue() < 0) {
                        throw new IllegalArgumentException("availabilityQuantity cannot be null or negative");
                }
                if (recommendationState == null || recommendationState.isBlank() || RecommendationStates.valueOf(recommendationState) == null) {
                        throw new IllegalArgumentException("recommendationState cannot be null or blank");
                }
                if (priority == null || priority.isBlank() || Priority.valueOf(priority) == null) {
                        throw new IllegalArgumentException("priority cannot be null or blank");
                }
                if (expirationDate == null || expirationDate.isBlank()) {
                        throw new IllegalArgumentException("expirationDate cannot be null or blank");
                }
                if (groupType == null || groupType.isBlank()) {
                        throw new IllegalArgumentException("groupType cannot be null or blank");
                }
                if (price == null || price.doubleValue() < 0) {
                        throw new IllegalArgumentException("price cannot be null or negative");
                }
        }
}
