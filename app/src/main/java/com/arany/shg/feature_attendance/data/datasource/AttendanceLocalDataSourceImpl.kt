package com.arany.shg.feature_attendance.data.datasource

import com.arany.shg.data.db.AttendanceDAO
import com.arany.shg.feature_attendance.data.model.Attendance
import javax.inject.Inject

class AttendanceLocalDataSourceImpl @Inject constructor(private val attendanceDao: AttendanceDAO): AttendanceLocalDataSource {
    override suspend fun createAttendance(attendance: Attendance) {
        attendanceDao.addAttendance(attendance)
    }

    override suspend fun createAttendances(attendances: List<Attendance>) {
        attendanceDao.addAttendances(attendances = attendances)
    }

    override suspend fun getAttendances(committeeId: Int): List<Attendance> {
        return attendanceDao.getAttendances(committeeId = committeeId)
    }

    override suspend fun getAttendance(memberId: Int): Attendance? {
        return attendanceDao.getAttendance(memberId = memberId)
    }

    override suspend fun updateAttendance(attendance: Attendance) {
        attendanceDao.updateAttendance(attendance)
    }

    override suspend fun deleteAttendance(attendance: Attendance) {
        attendanceDao.deleteAttendance(attendance)
    }
}