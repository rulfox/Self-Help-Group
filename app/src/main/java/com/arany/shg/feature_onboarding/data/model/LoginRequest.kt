package com.arany.shg.feature_onboarding.data.model

data class LoginRequest(
    val phoneNumber: String?,
    val password: String?
)
class InvalidLoginException(message: String): Exception(message)