package com.arany.shg.feature_member.presentation.util.add_member

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.*
import com.arany.shg.data.models.Committee
import com.arany.shg.data.models.Role
import com.arany.shg.data.util.Resource
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_onboarding.data.model.InvalidLoginException
import com.arany.shg.feature_role.domain.usecase.RoleUseCases
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import com.arany.shg.feature_shg.domain.use_case.SelfHelpGroupUseCases
import com.arany.shg.feature_shg.presentation.create_shg.SelfHelpGroupEvent
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMemberViewModel @Inject constructor(
    private val memberUseCases: MemberUseCases,
    private val roleUseCases: RoleUseCases,
): ViewModel() {

    private var _role = mutableStateOf(RoleState(hint = "Select Role"))
    var role: State<RoleState> = _role

    private var _name = mutableStateOf(TextFieldState(hint = "Enter Name"))
    var name: State<TextFieldState> = _name

    private var _address = mutableStateOf(TextFieldState(hint = "Enter Address"))
    var address: State<TextFieldState> = _address

    private var _phoneNumber = mutableStateOf(TextFieldState(hint = "Enter Phone Number"))
    var phoneNumber: State<TextFieldState> = _phoneNumber

    private var _email = mutableStateOf(TextFieldState(hint = "Enter Email"))
    var email: State<TextFieldState> = _email

    private var _password = mutableStateOf(TextFieldState(hint = "Select Password"))
    var password: State<TextFieldState> = _password

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val getRolesExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch { _eventFlow.emit(UiEvent.ShowError("Failed Loading Roles ->"+throwable.message.toString())) }
    }

    private val addMemberExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch { _eventFlow.emit(UiEvent.ShowError("Add Member Failed ->"+throwable.message.toString())) }
    }

    init {
        viewModelScope.launch(Dispatchers.IO + getRolesExceptionHandler) {
            when(val fetchedRole = roleUseCases.getRolesUseCase()){
                is Resource.Success -> {
                    _role.value = _role.value.copy(listOfRoles = fetchedRole.data?: arrayListOf())
                }
                is Resource.Error -> {
                    _eventFlow.emit(UiEvent.ShowError("Failed Loading Roles"))
                }
                is Resource.Loading ->{
                    //Do Nothing, Since its from Database
                }
            }
        }
    }

    fun onEvent(event: AddMemberEvent) {
        when(event){
            is AddMemberEvent.EnteredName -> {
                _name.value = _name.value.copy(text = event.name)
            }
            is AddMemberEvent.EnteredAddress -> {
                _address.value = _name.value.copy(text = event.address)
            }
            is AddMemberEvent.EnteredPhoneNumber -> {
                _phoneNumber.value = _phoneNumber.value.copy(text = event.phoneNumber)
            }
            is AddMemberEvent.EnteredEmail -> {
                _email.value = _email.value.copy(text = event.email)
            }
            is AddMemberEvent.SelectedRole -> {
                _role.value = _role.value.copy(role = event.role)
            }
            is AddMemberEvent.AddMember -> {
                viewModelScope.launch(Dispatchers.IO + addMemberExceptionHandler) {
                    memberUseCases.createMemberUseCase(Member(shgId = Constants.ShgId, name = _name.value.text, address = _address.value.text, phoneNumber = _phoneNumber.value.text, emailId = _email.value.text, password = _password.value.text))
                    _eventFlow.emit(UiEvent.MemberAdded)
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String): UiEvent()
        object MemberAdded: UiEvent()
    }
}