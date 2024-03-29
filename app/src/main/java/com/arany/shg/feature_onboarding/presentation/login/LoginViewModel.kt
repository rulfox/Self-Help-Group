package com.arany.shg.feature_onboarding.presentation.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.Constants
import com.arany.shg.data.util.Resource
import com.arany.shg.feature_onboarding.data.model.InvalidLoginException
import com.arany.shg.feature_onboarding.data.model.LoginRequest
import com.arany.shg.feature_onboarding.domain.use_case.LoginUseCases
import com.arany.shg.feature_shg.domain.use_case.SelfHelpGroupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases,
    private val selfHelpGroupUseCases: SelfHelpGroupUseCases
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
            is LoginEvent.VerifyLogin -> {
                Log.e("Login","LoginEvent.VerifyLogin Triggered")
                viewModelScope.launch {
                    try {
                        val loggedInSelfHelpGroup = loginUseCases.verifyLoginUseCase(LoginRequest(phoneNumber.value.text, password.value.text))
                        Log.e("LoggedInAs", loggedInSelfHelpGroup.toString())
                        Constants.ShgId = loggedInSelfHelpGroup.shgId
                        Constants.selfHelpGroup = loggedInSelfHelpGroup
                        _eventFlow.emit(UiEvent.LoginVerified)
                    }catch (e: InvalidLoginException){
                        Log.e("Login","LoginEvent.VerifyLogin Exception")
                        _eventFlow.emit(UiEvent.ShowSnackBarError(e.message?:"Couldn't authenticate"))
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBarError(val message: String): UiEvent()
        object CreateSelfHelpGroup: UiEvent()
        object LoginVerified: UiEvent()
    }
}