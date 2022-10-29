package com.arany.shg.feature_onboarding.domain.use_case

import com.arany.shg.data.util.Resource
import com.arany.shg.feature_onboarding.data.model.InvalidLoginException
import com.arany.shg.feature_onboarding.data.model.LoginRequest
import com.arany.shg.feature_onboarding.domain.repository.LoginRepository
import com.arany.shg.feature_shg.data.model.SelfHelpGroup

class VerifyLoginUseCase(private val loginRepository: LoginRepository) {
    @Throws(InvalidLoginException::class)
    suspend operator fun invoke(request: LoginRequest): SelfHelpGroup{
        if(request.phoneNumber.isNullOrBlank()) throw InvalidLoginException("Please enter phone number")
        if(request.password.isNullOrBlank()) throw InvalidLoginException("Please enter password")
        when(val loggedInShg = loginRepository.verifyLogin(request)){
            is Resource.Success -> {
                return loggedInShg.data?.let { it } ?: run{ throw InvalidLoginException("Please enter valid credentials") }
            }
            else -> throw InvalidLoginException("Please enter valid credentials")
        }
    }
}