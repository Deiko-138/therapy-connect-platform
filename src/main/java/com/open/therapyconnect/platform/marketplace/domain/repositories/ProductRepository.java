package com.open.therapyconnect.platform.marketplace.domain.repositories;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;

import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    boolean existsByProductName(String name);
}
