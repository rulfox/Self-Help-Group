package com.arany.shg.feature_onboarding.domain.use_case

import com.arany.shg.feature_onboarding.data.model.InvalidLoginException
import com.arany.shg.feature_onboarding.data.model.LoginRequest
import com.arany.shg.feature_onboarding.domain.repository.LoginRepository

class VerifyLoginUseCase(private val loginRepository: LoginRepository) {
    @Throws(InvalidLoginException::class)
    suspend operator fun invoke(request: LoginRequest): Int{
        if(request.phoneNumber.isNullOrBlank()) throw InvalidLoginException("Please enter phone number")
        if(request.password.isNullOrBlank()) throw InvalidLoginException("Please enter password")
        return loginRepository.verifyLogin(request) ?: throw InvalidLoginException("Please enter valid credentials")
    }
}