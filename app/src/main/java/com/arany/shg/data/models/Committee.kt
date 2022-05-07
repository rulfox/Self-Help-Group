package com.arany.shg.data.models


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = [ForeignKey(entity = SelfHelpGroup::class, parentColumns = ["shgId"], childColumns = ["shgId"], onDelete = CASCADE), ForeignKey(entity = Member::class, parentColumns = ["memberId"], childColumns = ["memberId"], onDelete = CASCADE)])
data class Committee(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("committeeId")
    val committeeId: Int?,
    @SerializedName("shgId")
    val shgId: Int?,
    @SerializedName("memberId")
    val memberId: Int?,
    @SerializedName("date")
    val date: String?
)