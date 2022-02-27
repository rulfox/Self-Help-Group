package com.arany.shg.feature_onboarding.domain.repository

import com.arany.shg.data.models.Member
import com.arany.shg.feature_onboarding.data.model.LoginRequest

interface LoginRepository {
    suspend fun verifyLogin(loginRequest: LoginRequest): Int?
}