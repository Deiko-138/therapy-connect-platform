package com.open.therapyconnect.platform.marketplace.domain.repositories;

import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;

import java.util.List;
import java.util.Optional;

public interface CatalogRepository {
    Catalog save(Catalog catalog);
    Optional<Catalog> findById(Long id);
    boolean existsByCatalogName(String name);
}
