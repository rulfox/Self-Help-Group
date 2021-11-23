package com.arany.shg.domain.repository

import com.arany.shg.data.models.Attendance
import com.arany.shg.data.util.Resource

interface AttendanceRepository{
      suspend fun createAttendance(attendance: Attendance)
      suspend fun getAttendance(committeeId: Int): Resource<Attendance>
      suspend fun updateAttendance(attendance: Attendance)
}