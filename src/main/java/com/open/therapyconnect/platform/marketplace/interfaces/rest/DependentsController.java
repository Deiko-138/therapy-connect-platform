package com.open.therapyconnect.platform.marketplace.interfaces.rest;

import com.open.therapyconnect.platform.marketplace.application.commandservices.DependentCommandService;
import com.open.therapyconnect.platform.marketplace.application.queryservices.DependentQueryService;
import com.open.therapyconnect.platform.marketplace.domain.model.aggregates.Dependent;
import com.open.therapyconnect.platform.marketplace.domain.model.queries.GetDependentByIdQuery;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.resources.CreateDependentResource;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.transform.CreateDependentCommandFromResourceAssembler;
import com.open.therapyconnect.platform.marketplace.interfaces.rest.transform.DependentResourceFromEntityAssembler;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import com.open.therapyconnect.platform.shared.interfaces.rest.transform.ResponseEntityAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/dependents")
public class DependentsController {
    private final DependentCommandService dependentCommandService;
    private final DependentQueryService dependentQueryService;

    public DependentsController(DependentCommandService dependentCommandService, DependentQueryService dependentQueryService) {
        this.dependentCommandService = dependentCommandService;
        this.dependentQueryService = dependentQueryService;
    }

    @PostMapping
    public ResponseEntity<?>  createDependent(@RequestBody CreateDependentResource resource) {
        var command = CreateDependentCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = this.dependentCommandService.handle(command)
                .flatMap(dependentId -> this.dependentQueryService.handle(new GetDependentByIdQuery(dependentId))
                        .<Result<Dependent, ApplicationError>>map(Result::success)
                        .orElseGet(() -> Result.failure(ApplicationError.notFound("Dependent", dependentId.toString()))));

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result, DependentResourceFromEntityAssembler::toResourceFromEntity, HttpStatus.CREATED
        );
    }
}
