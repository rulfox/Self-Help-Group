package com.arany.shg.feature_committee.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.arany.shg.data.models.Committee
import com.arany.shg.feature_attendance.data.model.Attendance
import com.arany.shg.feature_member.data.model.Member

data class CommitteeWithDetails (
    @Embedded
    val committee: Committee,
    @Relation(entity = Member::class, parentColumn = "memberId", entityColumn = "memberId")
    val member: Member,
    @Relation(entity = Attendance::class, parentColumn = "committeeId", entityColumn = "committeeId")
    val attendances: List<Attendance>
)