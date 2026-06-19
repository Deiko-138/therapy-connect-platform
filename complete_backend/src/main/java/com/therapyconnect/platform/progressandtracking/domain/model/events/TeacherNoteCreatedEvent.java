package com.therapyconnect.platform.progressandtracking.domain.model.events;

import com.therapyconnect.platform.progressandtracking.domain.model.aggregates.TeacherNote;

/**
 * Domain event raised when a teacher note is created.
 *
 * @param teacherNote the newly created teacher note
 */
public record TeacherNoteCreatedEvent(TeacherNote teacherNote) {
}
