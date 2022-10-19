package com.arany.shg.feature_onboarding.data.repository

import com.arany.shg.feature_onboarding.data.data_source.LoginLocalDataSource
import com.arany.shg.feature_onboarding.data.model.LoginRequest
import com.arany.shg.feature_onboarding.domain.repository.LoginRepository

class LoginRepositoryImpl(private val loginLocalDataSource: LoginLocalDataSource): LoginRepository {
    override suspend fun verifyLogin(loginRequest: LoginRequest): Int? {
        return loginLocalDataSource.verifyLogin(loginRequest)
    }
}