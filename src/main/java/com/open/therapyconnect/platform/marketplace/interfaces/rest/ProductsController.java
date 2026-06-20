package com.open.therapyconnect.platform.marketplace.interfaces.rest;

import com.open.therapyconnect.platform.marketplace.application.commandservices.ProductCommandService;
import com.open.therapyconnect.platform.marketplace.application.queryservices.ProductQueryService;
import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Product;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.GetProductByIdQuery;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.CreateProductResource;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.transform.CreateProductCommandFromResourceAssembler;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.transform.ProductResourceFromEntityAssembler;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import com.open.therapyconnect.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/products")
public class ProductsController {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductsController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @PostMapping
    public ResponseEntity<?>  createProduct(@RequestBody CreateProductResource resource) {
        var command = CreateProductCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = this.productCommandService.handle(command)
                .flatMap(productId -> this.productQueryService.handle(new GetProductByIdQuery(productId))
                        .<Result<Product, ApplicationError>>map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("Product", productId.toString()))));

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, ProductResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED
        );
    }
}
