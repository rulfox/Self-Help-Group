package com.arany.shg.domain.usecase.attendance

import com.arany.shg.data.models.Attendance
import com.arany.shg.domain.repository.AttendanceRepository

class CreateAttendanceUseCase(private val attendanceRepository: AttendanceRepository) {
    suspend fun execute(attendance: Attendance) = attendanceRepository.createAttendance(attendance)
}