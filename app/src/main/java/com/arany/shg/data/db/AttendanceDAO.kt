package com.arany.shg.data.db

import androidx.room.*
import com.arany.shg.feature_attendance.data.model.Attendance
import com.arany.shg.feature_member.data.model.Member
import kotlinx.coroutines.flow.Flow

@Dao
interface AttendanceDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAttendance(attendance: Attendance)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAttendances(attendances: List<Attendance>)

    @Query("SELECT * FROM Attendance WHERE committeeId = :committeeId")
    fun getAttendances(committeeId: Int): List<Attendance>

    @Query("SELECT * FROM Attendance WHERE memberId = :memberId")
    fun getAttendance(memberId: Int): Attendance?

    @Update
    suspend fun updateAttendance(attendance: Attendance)

    @Update
    suspend fun deleteAttendance(attendance: Attendance)
}