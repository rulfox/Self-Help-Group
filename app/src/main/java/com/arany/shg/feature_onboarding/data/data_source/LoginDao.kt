package com.arany.shg.feature_onboarding.data.data_source

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LoginDao {
    @Query("SELECT memberId FROM member WHERE phoneNumber = :phoneNumber AND password =:password")
    suspend fun verifyLogin(phoneNumber: String?, password: String?): Int?
}