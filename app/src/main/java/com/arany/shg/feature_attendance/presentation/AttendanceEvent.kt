package com.arany.shg.feature_attendance.presentation

import com.arany.shg.feature_member.domain.util.RoleType

sealed class AttendanceEvent{
    data class SetCommitteeId(val committeeId: Int): AttendanceEvent()
    data class IsPresentStatusChanged(val index: Int, val isPresent: Boolean): AttendanceEvent()
    data class IsLeaveAppliedStatusChanged(val index: Int, val hasApplied: Boolean): AttendanceEvent()
    object AddAttendance: AttendanceEvent()
}
