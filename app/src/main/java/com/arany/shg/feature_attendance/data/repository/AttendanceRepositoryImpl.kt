package com.arany.shg.feature_attendance.data.repository

import com.arany.shg.data.util.Resource
import com.arany.shg.feature_attendance.data.datasource.AttendanceLocalDataSource
import com.arany.shg.feature_attendance.data.model.Attendance
import com.arany.shg.feature_attendance.domain.repository.AttendanceRepository
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(private val attendanceLocalDataSource: AttendanceLocalDataSource): AttendanceRepository {
    override suspend fun createAttendance(attendance: Attendance) {
        attendanceLocalDataSource.createAttendance(attendance)
    }

    override suspend fun createAttendances(attendances: List<Attendance>) {
        attendanceLocalDataSource.createAttendances(attendances)
    }

    override suspend fun getAttendances(committeeId: Int): Resource<List<Attendance>> {
        val attendances = attendanceLocalDataSource.getAttendances(committeeId)
        return if(attendances.isEmpty())
            Resource.Error("No attendance found for committee with id $committeeId")
        else Resource.Success(attendances)
    }

    override suspend fun getAttendance(memberId: Int): Resource<Attendance> {
        return attendanceLocalDataSource.getAttendance(memberId)?.let { Resource.Success(it) } ?: Resource.Error("No attendance found for member with id $memberId")
    }

    override suspend fun updateAttendance(attendance: Attendance) {
        attendanceLocalDataSource.updateAttendance(attendance)
    }

    override suspend fun deleteAttendance(attendance: Attendance) {
        attendanceLocalDataSource.deleteAttendance(attendance)
    }

}