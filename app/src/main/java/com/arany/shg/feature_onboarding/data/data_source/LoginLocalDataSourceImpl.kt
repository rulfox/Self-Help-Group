package com.arany.shg.feature_onboarding.data.data_source

import com.arany.shg.feature_onboarding.data.model.LoginRequest

class LoginLocalDataSourceImpl(private val loginDao: LoginDao): LoginLocalDataSource {
    override suspend fun verifyLogin(loginRequest: LoginRequest): Int? {
        return loginDao.verifyLogin(loginRequest.phoneNumber, loginRequest.password)
    }
}