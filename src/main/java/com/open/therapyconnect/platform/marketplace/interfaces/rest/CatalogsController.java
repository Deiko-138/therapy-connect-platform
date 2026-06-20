package com.open.therapyconnect.platform.marketplace.interfaces.rest;

import com.open.therapyconnect.platform.marketplace.application.commandservices.CatalogCommandService;
import com.open.therapyconnect.platform.marketplace.application.queryservices.CatalogQueryService;
import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Catalog;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.GetCatalogByIdQuery;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.CreateCatalogResource;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.transform.CatalogResourceFromEntityAssembler;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.transform.CreateCatalogCommandFromResourceAssembler;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import com.open.therapyconnect.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/catalogs")
public class CatalogsController {
    private final CatalogCommandService catalogCommandService;
    private final CatalogQueryService catalogQueryService;

    public CatalogsController(CatalogCommandService catalogCommandService, CatalogQueryService catalogQueryService) {
        this.catalogCommandService = catalogCommandService;
        this.catalogQueryService = catalogQueryService;
    }

    @PostMapping
    public ResponseEntity<?>  createCatalog(@RequestBody CreateCatalogResource resource) {
        var command = CreateCatalogCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = this.catalogCommandService.handle(command)
                .flatMap(catalogId -> this.catalogQueryService.handle(new GetCatalogByIdQuery(catalogId))
                        .<Result<Catalog, ApplicationError>>map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("Catalog", catalogId.toString()))));

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, CatalogResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED
        );
    }
}
