package com.arany.shg.feature_attendance.domain.usecase

import com.arany.shg.feature_attendance.data.model.Attendance
import com.arany.shg.feature_attendance.domain.repository.AttendanceRepository

class CreateAttendancesUseCase(private val attendanceRepository: AttendanceRepository) {
    suspend operator fun invoke(attendances: List<Attendance>) = attendanceRepository.createAttendances(attendances)
}