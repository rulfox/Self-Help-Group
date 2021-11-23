package com.arany.shg.data.models


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = [ForeignKey(entity = Loan::class, parentColumns = ["loanId"], childColumns = ["loanId"], onDelete = CASCADE), ForeignKey(entity = Committee::class, parentColumns = ["committeeId"], childColumns = ["repaymentCommitteeId"], onDelete = CASCADE)])
data class LoanPayment(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("loanPaymentId")
    val loanPaymentId: Int?,
    @SerializedName("loanId")
    val loanId: Int?,
    @SerializedName("repaymentCommitteeId")
    val repaymentCommitteeId: Int?,
    @SerializedName("amount")
    val amount: Int?
)