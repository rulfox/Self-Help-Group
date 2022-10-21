package com.arany.shg.feature_attendance.data.model

data class AttendanceState (
    var memberId: Int,
    var memberName: String = "",
    var isPresent: Boolean = false,
    var leaveApplied: Boolean = false
)