package com.arany.shg.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Status(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("statusId")
    val statusId: Int?,
    @SerializedName("name")
    val name: String?
)