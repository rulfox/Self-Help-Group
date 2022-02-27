package com.arany.shg.feature_onboarding.data.data_source

import androidx.room.Dao
import androidx.room.Query
import com.arany.shg.data.models.Member
import com.arany.shg.feature_onboarding.presentation.login.LoginEvent

@Dao
interface LoginDao {
    @Query("SELECT memberId FROM member WHERE phoneNumber = :phoneNumber")
    fun verifyLogin(phoneNumber: String?, password: String?): Int?
}