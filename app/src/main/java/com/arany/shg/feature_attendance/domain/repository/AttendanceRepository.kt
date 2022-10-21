package com.arany.shg.feature_attendance.domain.repository

import com.arany.shg.feature_attendance.data.model.Attendance
import com.arany.shg.data.util.Resource

interface AttendanceRepository{
      suspend fun createAttendance(attendance: Attendance)
      suspend fun createAttendances(attendances: List<Attendance>)
      suspend fun getAttendances(committeeId: Int): Resource<List<Attendance>>
      suspend fun getAttendance(memberId: Int): Resource<Attendance>
      suspend fun updateAttendance(attendance: Attendance)
      suspend fun deleteAttendance(attendance: Attendance)
}