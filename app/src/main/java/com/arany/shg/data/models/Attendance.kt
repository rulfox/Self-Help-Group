package com.arany.shg.data.models


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = [ForeignKey(entity = Committee::class, parentColumns = ["committeeId"], childColumns = ["committeeId"], onDelete = CASCADE)])
data class Attendance(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("attendanceId")
    val attendanceId: Int,
    @SerializedName("committeeId")
    val committeeId: Int,
    @SerializedName("presentMembers")
    val presentMembers: ArrayList<Int> = arrayListOf()
)