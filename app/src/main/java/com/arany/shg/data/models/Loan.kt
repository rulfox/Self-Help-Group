package com.arany.shg.data.models


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = [ForeignKey(entity = Committee::class, parentColumns = ["committeeId"], childColumns = ["committeeId"], onDelete = CASCADE), ForeignKey(entity = Member::class, parentColumns = ["memberId"], childColumns = ["memberId"], onDelete = CASCADE), ForeignKey(entity = Status::class, parentColumns = ["statusId"], childColumns = ["statusId"], onDelete = CASCADE)])
data class Loan(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("loanId")
    val loanId: Int?,
    @SerializedName("committeeId")
    val committeeId: Int?,
    @SerializedName("memberId")
    val memberId: Int?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("amount")
    val amount: Double?,
    @SerializedName("interest")
    val interest: Double?,
    @SerializedName("statusId")
    val statusId: Int?
)