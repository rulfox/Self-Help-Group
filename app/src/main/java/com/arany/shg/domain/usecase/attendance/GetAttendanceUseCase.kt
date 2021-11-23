package com.arany.shg.domain.usecase.attendance

import com.arany.shg.domain.repository.AttendanceRepository

class GetAttendanceUseCase(private val attendanceRepository: AttendanceRepository) {
    suspend fun execute(committeeId: Int) = attendanceRepository.getAttendance(committeeId)
}