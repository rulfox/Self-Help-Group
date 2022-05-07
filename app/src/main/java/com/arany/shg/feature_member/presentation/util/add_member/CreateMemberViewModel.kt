package com.arany.shg.feature_member.presentation.util.add_member

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_onboarding.presentation.login.LoginTextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateMemberViewModel @Inject constructor(private val useCases: MemberUseCases): ViewModel() {
    private var _name = mutableStateOf(LoginTextFieldState(hint = "Enter name"))
    val name: State<LoginTextFieldState> = _name

    private var _address = mutableStateOf(LoginTextFieldState(hint = "Enter address"))
    val address: State<LoginTextFieldState> = _address

    private var _phone = mutableStateOf(LoginTextFieldState(hint = "Enter phone number"))
    val phone: State<LoginTextFieldState> = _phone

    private var _email = mutableStateOf(LoginTextFieldState(hint = "Enter email"))
    val email: State<LoginTextFieldState> = _email

    private var _role = mutableStateOf(LoginTextFieldState(hint = "Enter role"))
    val role: State<LoginTextFieldState> = _role

    fun onEvent(event: MemberEvent){
        when(event){
            is MemberEvent.EnteredName ->{

            }
            is MemberEvent.EnteredAddress ->{

            }
            is MemberEvent.EnteredPhoneNumber ->{

            }
            is MemberEvent.EnteredEmail ->{

            }
            is MemberEvent.EnteredRole ->{

            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBarError(val message: String): UiEvent()
        object LoginVerified: UiEvent()
    }
}