package com.open.therapyconnect.platform.marketplace.interfaces.rest.resources;

import com.open.therapyconnect.platform.marketplace.domain.model.valueobjects.ProgressStates;

public record CreateDependentResource(String dependentName, String dependentCondition, String needLevel,
                                      String progressState) {
        public CreateDependentResource {
                if (dependentName == null || dependentName.isBlank()) {
                        throw new IllegalArgumentException("dependentName cannot be null or blank");
                }
                if (dependentCondition == null || dependentCondition.isBlank()) {
                        throw new IllegalArgumentException("dependentCondition cannot be null or blank");
                }
                if (needLevel == null || needLevel.isBlank()) {
                        throw new IllegalArgumentException("needLevel cannot be null or blank");
                }
                if (progressState == null || progressState.isBlank() || ProgressStates.valueOf(progressState) == null) {
                        throw new IllegalArgumentException("progressState cannot be null or blank");
                }
        }
}
