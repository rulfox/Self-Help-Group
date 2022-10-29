package com.arany.shg.feature_onboarding.data.repository

import com.arany.shg.data.util.Resource
import com.arany.shg.feature_onboarding.data.data_source.LoginLocalDataSource
import com.arany.shg.feature_onboarding.data.model.LoginRequest
import com.arany.shg.feature_onboarding.domain.repository.LoginRepository
import com.arany.shg.feature_shg.data.model.SelfHelpGroup

class LoginRepositoryImpl(private val loginLocalDataSource: LoginLocalDataSource): LoginRepository {
    override suspend fun verifyLogin(loginRequest: LoginRequest): Resource<SelfHelpGroup> {
        return loginLocalDataSource.verifyLogin(loginRequest)?.let {
            Resource.Success(it)
        } ?: run {
            Resource.Error("Unable to find a matching login from db")
        }
    }
}