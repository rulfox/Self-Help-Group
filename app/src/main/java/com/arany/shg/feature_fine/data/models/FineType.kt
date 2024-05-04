package com.arany.shg.feature_fine.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class FineType(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("fineTypeId")
    val fineTypeId: Int?,
    @SerializedName("name")
    val name: String
)