package com.arany.shg.feature_attendance.data.model

enum class AttendanceStatus(val value: Int) {
    PRESENT(0),
    ABSENT(1),
    LEAVE(2)
}