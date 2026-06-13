package com.open.therapyconnect.platform.scheduling.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Session response resource.
 */
@Schema(name = "SessionResponse", description = "Session information response")
public record SessionResource(
        @Schema(description = "Session unique identifier", example = "1")
        Long id,

        @Schema(description = "Session title", example = "Psicomotricidad - Mateo")
        String title,

        @Schema(description = "Session date", example = "2026-05-20")
        String sessionDate,

        @Schema(description = "Start time", example = "09:00")
        String startTime,

        @Schema(description = "End time", example = "10:00")
        String endTime,

        @Schema(description = "Session type", example = "privada")
        String sessionType,

        @Schema(description = "Session status", example = "pending")
        String sessionStatus,

        @Schema(description = "Teacher ID", example = "1")
        Long teacherId,

        @Schema(description = "Student ID", example = "1")
        Long studentId
) {}