package com.arany.shg.feature_onboarding.data.data_source

import com.arany.shg.feature_onboarding.data.model.LoginRequest

interface LoginLocalDataSource {
    suspend fun verifyLogin(loginRequest: LoginRequest): Int?
}