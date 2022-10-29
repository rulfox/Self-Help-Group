package com.arany.shg.feature_onboarding.data.data_source

import androidx.room.Dao
import androidx.room.Query
import com.arany.shg.feature_shg.data.model.SelfHelpGroup

@Dao
interface LoginDao {
    @Query("SELECT * FROM SelfHelpGroup WHERE name = :name AND password =:password")
    suspend fun verifyLogin(name: String?, password: String?): SelfHelpGroup?
}