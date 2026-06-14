package com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.adapters;

import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.WeeklySchedule;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.WeeklyScheduleRepository;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.assemblers.WeeklySchedulePersistenceAssembler;
import com.open.therapyconnect.platform.sessionandlive.infrastructure.persistence.jpa.repositories.WeeklySchedulePersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WeeklyScheduleRepositoryImpl implements WeeklyScheduleRepository {

    private final WeeklySchedulePersistenceRepository persistenceRepository;

    public WeeklyScheduleRepositoryImpl(WeeklySchedulePersistenceRepository persistenceRepository) {
        this.persistenceRepository = persistenceRepository;
    }

    @Override
    public Optional<WeeklySchedule> findById(Long id) {
        return persistenceRepository.findById(id)
                .map(WeeklySchedulePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<WeeklySchedule> findAll() {
        return persistenceRepository.findAll().stream()
                .map(WeeklySchedulePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<WeeklySchedule> findByTeacherId(Long teacherId) {
        return persistenceRepository.findByTeacherId(teacherId).stream()
                .map(WeeklySchedulePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public WeeklySchedule save(WeeklySchedule schedule) {
        var saved = persistenceRepository.save(WeeklySchedulePersistenceAssembler.toPersistenceFromDomain(schedule));
        return WeeklySchedulePersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsById(Long id) {
        return persistenceRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        persistenceRepository.deleteById(id);
    }
}
