package com.arany.shg.feature_shg.data.model


import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "SelfHelpGroup", indices = [Index(value = ["name"], unique = true)])
data class SelfHelpGroup(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("shgId")
    val shgId: Int = 0,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("password")
    val password: String
)