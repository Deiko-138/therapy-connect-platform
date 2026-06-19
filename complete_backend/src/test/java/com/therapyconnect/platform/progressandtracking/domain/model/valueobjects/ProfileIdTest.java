package com.therapyconnect.platform.progressandtracking.domain.model.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileIdTest {

    @Test
    void profileIdWithValidValueCreatesSuccessfully() {
        var profileId = new ProfileId(1L);
        assertEquals(1L, profileId.id());
    }

    @Test
    void profileIdWithNullThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ProfileId(null));
    }

    @Test
    void profileIdWithZeroThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ProfileId(0L));
    }

    @Test
    void profileIdWithNegativeThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ProfileId(-5L));
    }
}
