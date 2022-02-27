package com.arany.shg.feature_onboarding.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.feature_onboarding.data.model.InvalidLoginException
import com.arany.shg.feature_onboarding.data.model.LoginRequest
import com.arany.shg.feature_onboarding.domain.use_case.LoginUseCases
import com.arany.shg.feature_onboarding.domain.use_case.VerifyLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
): ViewModel() {
    private var _phoneNumber = mutableStateOf(LoginTextFieldState(hint = "Enter phone number"))
    var phoneNumber: State<LoginTextFieldState> = _phoneNumber

    private var _password = mutableStateOf(LoginTextFieldState(hint = "Enter password"))
    var password: State<LoginTextFieldState> = _password

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: LoginEvent) {
        when(event){
            is LoginEvent.EnteredPhoneNumber -> {
                _phoneNumber.value = _phoneNumber.value.copy(text = event.phoneNumber)
            }
            is LoginEvent.EnteredPassword -> {
                _password.value = _password.value.copy(text = event.password)
            }
            is LoginEvent.LoginVerified -> {
                viewModelScope.launch {
                    try {
                        loginUseCases.verifyLoginUseCase(LoginRequest(phoneNumber.value.text, password.value.text))
                        _eventFlow.emit(UiEvent.LoginVerified)
                    }catch (e: InvalidLoginException){
                        _eventFlow.emit(UiEvent.ShowSnackBarError(e.message?:"Couldn't authenticate"))
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBarError(val message: String): UiEvent()
        object LoginVerified: UiEvent()
    }
}