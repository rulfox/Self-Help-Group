package com.arany.shg.data.models


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = [ForeignKey(entity = Fine::class, parentColumns = ["fineId"], childColumns = ["fineId"], onDelete = CASCADE), ForeignKey(entity = Committee::class, parentColumns = ["committeeId"], childColumns = ["repaymentCommitteeId"], onDelete = CASCADE)])
data class FinePayment(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("finePaymentId")
    val finePaymentId: Int?,
    @SerializedName("fineId")
    val fineId: Int?,
    @SerializedName("repaymentCommitteeId")
    val repaymentCommitteeId: Int?,
    @SerializedName("amount")
    val amount: Int?
)