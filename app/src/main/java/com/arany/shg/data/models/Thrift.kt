package com.arany.shg.data.models


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.arany.shg.feature_member.data.model.Member
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = [ForeignKey(entity = Committee::class, parentColumns = ["committeeId"], childColumns = ["committeeId"], onDelete = CASCADE), ForeignKey(entity = Member::class, parentColumns = ["memberId"], childColumns = ["memberId"], onDelete = CASCADE)])
data class Thrift(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("thriftId")
    val thriftId: Int?,
    @SerializedName("thriftTypeId")
    val thriftTypeId: Int?,
    @SerializedName("committeeId")
    val committeeId: Int?,
    @SerializedName("memberId")
    val memberId: Int?,
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("statusId")
    val statusId: Int?,
    @SerializedName("dateTime")
    val dateTime: String?,
)