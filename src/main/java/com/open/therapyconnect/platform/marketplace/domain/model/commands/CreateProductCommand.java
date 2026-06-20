package com.open.therapyconnect.platform.marketplace.domain.model.commands;

public record CreateProductCommand(String productName, String productCategory, String productType, String availabilityState, Number availableQuantity, String recommendationState, String priority, String expirationDate, String groupType, Double price) {
    public CreateProductCommand {
        if (productName == null)
            throw new IllegalArgumentException("Product name cannot be null or blank");
        if (productCategory == null)
            throw new IllegalArgumentException("Product category cannot be null or blank");
        if (productType == null)
            throw new IllegalArgumentException("Product type cannot be null or blank");
        if (availableQuantity == null)
            throw new IllegalArgumentException("Available quantity cannot be null");
        if (priority == null)
            throw new IllegalArgumentException("Priority cannot be null or blank");
        if (expirationDate == null)
            throw new IllegalArgumentException("Expiration Date cannot be null or blank");
        if (groupType == null)
            throw new IllegalArgumentException("Group type cannot be null or blank");
        if (price == null)
            throw new IllegalArgumentException("Price cannot be null or blank");
    }
}
