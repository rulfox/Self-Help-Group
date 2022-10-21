package com.arany.shg.feature_attendance.data.datasource

import com.arany.shg.data.util.Resource
import com.arany.shg.feature_attendance.data.model.Attendance

interface AttendanceLocalDataSource {
    suspend fun createAttendance(attendance: Attendance)
    suspend fun createAttendances(attendances: List<Attendance>)
    suspend fun getAttendances(committeeId: Int): List<Attendance>
    suspend fun getAttendance(memberId: Int): Attendance?
    suspend fun updateAttendance(attendance: Attendance)
    suspend fun deleteAttendance(attendance: Attendance)
}