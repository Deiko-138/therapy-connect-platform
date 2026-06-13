package com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.scheduling.domain.model.aggregates.Reminder;
import com.open.therapyconnect.platform.scheduling.domain.repositories.ReminderRepository;
import com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.assemblers.ReminderPersistenceAssembler;
import com.open.therapyconnect.platform.scheduling.infrastructure.persistence.jpa.repositories.ReminderPersistenceRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository adapter that bridges the reminder domain repository port with Spring Data JPA.
 */
@Repository
public class ReminderRepositoryImpl implements ReminderRepository {

    private final ReminderPersistenceRepository reminderPersistenceRepository;

    public ReminderRepositoryImpl(ReminderPersistenceRepository reminderPersistenceRepository) {
        this.reminderPersistenceRepository = reminderPersistenceRepository;
    }

    @Override
    public Optional<Reminder> findById(Long id) {
        return reminderPersistenceRepository.findById(id)
                .map(ReminderPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Reminder> findAll() {
        return reminderPersistenceRepository.findAll().stream()
                .map(ReminderPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public Reminder save(Reminder reminder) {
        var saved = reminderPersistenceRepository.save(
                ReminderPersistenceAssembler.toPersistenceFromDomain(reminder));
        return ReminderPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsById(Long id) {
        return reminderPersistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        reminderPersistenceRepository.deleteById(id);
    }
}