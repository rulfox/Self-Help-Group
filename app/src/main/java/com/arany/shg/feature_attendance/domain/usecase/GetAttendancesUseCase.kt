package com.arany.shg.feature_attendance.domain.usecase

import com.arany.shg.feature_attendance.domain.repository.AttendanceRepository

class GetAttendancesUseCase(private val attendanceRepository: AttendanceRepository) {
    suspend operator fun invoke(committeeId: Int) = attendanceRepository.getAttendances(committeeId)
}