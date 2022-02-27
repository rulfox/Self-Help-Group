package com.arany.shg.feature_onboarding.presentation.login

sealed class LoginEvent {
    data class EnteredPhoneNumber(val phoneNumber: String): LoginEvent()
    data class EnteredPassword(val password: String): LoginEvent()
    object LoginVerified: LoginEvent()
}