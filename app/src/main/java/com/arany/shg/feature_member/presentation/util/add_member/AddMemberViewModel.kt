package com.arany.shg.feature_member.presentation.util.add_member

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.Constants
import com.arany.shg.core.util.RoleState
import com.arany.shg.core.util.TextFieldState
import com.arany.shg.data.models.Role
import com.arany.shg.data.util.Resource
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_role.domain.usecase.RoleUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMemberViewModel @Inject constructor(
    private val memberUseCases: MemberUseCases,
    private val roleUseCases: RoleUseCases,
): ViewModel() {

    private var _roles = MutableStateFlow<List<Role>>(arrayListOf())
    var roles: StateFlow<List<Role>> = _roles

    private var _role = mutableStateOf(RoleState(hint = "Select Role", listOfRoles = arrayListOf()))
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

    private lateinit var member: Member

    private val getRolesExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch { _eventFlow.emit(UiEvent.ShowError("Failed Loading Roles ->"+throwable.message.toString())) }
    }

    private val addMemberExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch { _eventFlow.emit(UiEvent.ShowError("Add Member Failed ->"+throwable.message.toString())) }
    }

    init {
        viewModelScope.launch(Dispatchers.IO + getRolesExceptionHandler) {
            roleUseCases.addRoleUseCase(Role(name = "Member"))
            roleUseCases.addRoleUseCase(Role(name = "Secretary"))
            roleUseCases.addRoleUseCase(Role(name = "President"))
            roleUseCases.addRoleUseCase(Role(name = "Treasurer"))

            when(val fetchedRole = roleUseCases.getRolesUseCase()){
                is Resource.Success -> {
                    _roles.value = fetchedRole.data ?: arrayListOf()
                }
                is Resource.Error -> {
                    _eventFlow.emit(UiEvent.ShowError("Failed Loading Roles"))
                }
                is Resource.Loading ->{
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
            is AddMemberEvent.EnteredPassword -> {
                _password.value = _password.value.copy(text = event.password)
            }
            is AddMemberEvent.AddMember -> {
                viewModelScope.launch(Dispatchers.IO + addMemberExceptionHandler) {
                    if(_name.value.text.isBlank())
                        _eventFlow.emit(UiEvent.ShowError("Please enter name"))
                    else if(_address.value.text.isBlank())
                        _eventFlow.emit(UiEvent.ShowError("Please enter address"))
                    else if(_phoneNumber.value.text.isBlank())
                        _eventFlow.emit(UiEvent.ShowError("Please enter phone Number"))
                    else if(_email.value.text.isBlank())
                        _eventFlow.emit(UiEvent.ShowError("Please enter email"))
                    else if(_role.value.role == null)
                        _eventFlow.emit(UiEvent.ShowError("Please enter role"))
                    else if(_password.value.text.isBlank())
                        _eventFlow.emit(UiEvent.ShowError("Please enter password"))
                    else {
                        val member = Member(shgId = Constants.ShgId, name = _name.value.text, address = _address.value.text, phoneNumber = _phoneNumber.value.text, emailId = _email.value.text, password = _password.value.text, roleId = _role.value.role?.roleId)
                        Log.e("Member", member.toString())
                        memberUseCases.createMemberUseCase(member)
                        _eventFlow.emit(UiEvent.MemberAdded)
                        memberUseCases.getMembersByShgIdUseCase(1).collect{
                            it.forEach {
                                Log.e("Member",it.toString())
                            }
                        }
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String): UiEvent()
        object MemberAdded: UiEvent()
    }
}