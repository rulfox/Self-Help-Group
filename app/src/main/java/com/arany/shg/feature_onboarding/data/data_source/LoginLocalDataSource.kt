package com.arany.shg.feature_onboarding.data.data_source

import com.arany.shg.feature_onboarding.data.model.LoginRequest
import com.arany.shg.feature_shg.data.model.SelfHelpGroup

interface LoginLocalDataSource {
    suspend fun verifyLogin(loginRequest: LoginRequest): SelfHelpGroup?
}