package com.open.therapyconnect.platform.scheduling.domain.repositories;

import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Reminder;
import java.util.List;
import java.util.Optional;

/** Reminder repository port. */
public interface ReminderRepository {
    Optional<Reminder> findById(Long id);
    List<Reminder> findAll();
    Reminder save(Reminder reminder);
    boolean existsById(Long id);
    void deleteById(Long id);
}