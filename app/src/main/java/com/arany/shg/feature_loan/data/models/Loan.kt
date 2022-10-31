package com.arany.shg.feature_loan.data.models


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.arany.shg.data.models.Committee
import com.arany.shg.data.models.Status
import com.arany.shg.feature_member.data.model.Member
import com.google.gson.annotations.SerializedName

@Entity(tableName = "loan", foreignKeys = [ForeignKey(entity = Committee::class, parentColumns = ["committeeId"], childColumns = ["committeeId"], onDelete = CASCADE), ForeignKey(entity = Member::class, parentColumns = ["memberId"], childColumns = ["memberId"], onDelete = CASCADE)/*, ForeignKey(entity = Status::class, parentColumns = ["statusId"], childColumns = ["statusId"], onDelete = CASCADE)*/])
data class Loan(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("loanId")
    val loanId: Int?,
    @SerializedName("committeeId")
    val committeeId: Int?,
    @SerializedName("memberId")
    val memberId: Int?,
    @SerializedName("dateTime")
    val dateTime: String,
    @SerializedName("amount")
    val amount: Double = 0.0,
    @SerializedName("interest")
    val interest: Double = 0.0,
    @SerializedName("isLoanCompleted")
    val isLoanCompleted: Boolean = false
)