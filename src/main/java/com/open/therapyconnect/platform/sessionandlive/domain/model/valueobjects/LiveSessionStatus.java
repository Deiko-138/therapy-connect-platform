package com.open.therapyconnect.platform.sessionandlive.domain.model.valueobjects;

/**
 * Lifecycle status of a live session.
 */
public enum LiveSessionStatus {
    SCHEDULED,
    STARTING,
    LIVE,
    ENDED,
    CANCELLED
}
