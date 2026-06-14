package com.open.therapyconnect.platform.sessionandlive.application.internal.commandservices;

import com.open.therapyconnect.platform.sessionandlive.application.commandservices.SessionAttendanceCommandService;
import com.open.therapyconnect.platform.sessionandlive.domain.model.aggregates.SessionAttendance;
import com.open.therapyconnect.platform.sessionandlive.domain.model.commands.*;
import com.open.therapyconnect.platform.sessionandlive.domain.repositories.SessionAttendanceRepository;
import com.open.therapyconnect.platform.shared.application.result.ApplicationError;
import com.open.therapyconnect.platform.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class SessionAttendanceCommandServiceImpl implements SessionAttendanceCommandService {

    private final SessionAttendanceRepository attendanceRepository;

    public SessionAttendanceCommandServiceImpl(SessionAttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Result<Long, ApplicationError> handle(RecordAttendanceCommand command) {
        var attendance = new SessionAttendance(command);
        try {
            attendance = attendanceRepository.save(attendance);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("record-attendance", e.getMessage()));
        }
        return Result.success(attendance.getId());
    }

    @Override
    public Result<SessionAttendance, ApplicationError> handle(UpdateAttendanceCommand command) {
        var result = attendanceRepository.findById(command.attendanceId());
        if (result.isEmpty())
            return Result.failure(ApplicationError.notFound("SessionAttendance", command.attendanceId().toString()));
        try {
            var updated = attendanceRepository.save(
                    result.get().updateStatus(command.attendanceStatus(), command.remarks()));
            return Result.success(updated);
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("update-attendance", e.getMessage()));
        }
    }

    @Override
    public Result<Long, ApplicationError> handle(DeleteAttendanceCommand command) {
        if (!attendanceRepository.existsById(command.attendanceId()))
            return Result.failure(ApplicationError.notFound("SessionAttendance", command.attendanceId().toString()));
        try {
            attendanceRepository.deleteById(command.attendanceId());
            return Result.success(command.attendanceId());
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected("delete-attendance", e.getMessage()));
        }
    }
}
