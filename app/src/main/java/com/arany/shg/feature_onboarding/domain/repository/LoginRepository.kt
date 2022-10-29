package com.arany.shg.feature_onboarding.domain.repository

import com.arany.shg.data.util.Resource
import com.arany.shg.feature_onboarding.data.model.LoginRequest
import com.arany.shg.feature_shg.data.model.SelfHelpGroup

interface LoginRepository {
    suspend fun verifyLogin(loginRequest: LoginRequest): Resource<SelfHelpGroup>
}