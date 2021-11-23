package com.arany.shg.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Role(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("roleId")
    val roleId: Int?,
    @SerializedName("name")
    val name: String?
)