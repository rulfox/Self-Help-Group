package com.arany.shg.feature_fine.data.models


import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.arany.shg.data.models.Committee
import com.arany.shg.feature_loan.data.models.Loan
import com.arany.shg.feature_member.data.model.Member
import com.google.gson.annotations.SerializedName

data class FineWithDetails(
    @Embedded
    val committee: Committee,
    @Relation(entity = Member::class, parentColumn = "memberId", entityColumn = "memberId")
    val member: Member,
    @Relation(entity = Fine::class, parentColumn = "committeeId", entityColumn = "committeeId")
    val fines: List<Fine>
)