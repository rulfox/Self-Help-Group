package com.arany.shg.feature_attendance.domain.usecase

import com.arany.shg.feature_attendance.domain.repository.AttendanceRepository

class GetAttendanceUseCase(private val attendanceRepository: AttendanceRepository) {
    suspend operator fun invoke(memberId: Int) = attendanceRepository.getAttendance(memberId)
}