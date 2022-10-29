package com.arany.shg.data.models


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(indices = [Index(value = ["name"], unique = true)])
data class Role(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("roleId")
    val roleId: Int?= null,
    @SerializedName("name")
    val name: String?,
    @SerializedName("canWrite")
    val canWrite: Boolean = false
)