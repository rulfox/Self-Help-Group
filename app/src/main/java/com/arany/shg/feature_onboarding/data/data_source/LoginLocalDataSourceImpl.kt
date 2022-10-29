package com.arany.shg.feature_onboarding.data.data_source

import com.arany.shg.feature_onboarding.data.model.LoginRequest
import com.arany.shg.feature_shg.data.model.SelfHelpGroup

class LoginLocalDataSourceImpl(private val loginDao: LoginDao): LoginLocalDataSource {
    override suspend fun verifyLogin(loginRequest: LoginRequest): SelfHelpGroup? {
        return loginDao.verifyLogin(loginRequest.phoneNumber, loginRequest.password)
    }
}