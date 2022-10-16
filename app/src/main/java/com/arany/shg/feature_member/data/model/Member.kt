package com.arany.shg.feature_member.data.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.arany.shg.data.models.Role
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Member", foreignKeys = [ForeignKey(entity = SelfHelpGroup::class, parentColumns = ["shgId"], childColumns = ["shgId"], onDelete = CASCADE)/*, ForeignKey(entity = Role::class, parentColumns = ["roleId"], childColumns = ["roleId"], onDelete = CASCADE)*/])
data class Member(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("memberId")
    val memberId: Int ?= null,
    @SerializedName("shgId")
    val shgId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("emailId")
    val emailId: String,
    /*@SerializedName("roleId")
    val roleId: Int,*/
    @SerializedName("password")
    val password: String?
)