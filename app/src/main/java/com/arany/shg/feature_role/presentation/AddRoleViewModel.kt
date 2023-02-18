package com.arany.shg.feature_role.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.*
import com.arany.shg.data.models.Committee
import com.arany.shg.data.models.Role
import com.arany.shg.data.util.DateUtils
import com.arany.shg.data.util.DateUtils.toFormattedString
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_role.domain.usecase.RoleUseCases
import com.arany.shg.feature_thrift.data.model.Thrift
import com.arany.shg.feature_thrift.domain.usecase.ThriftUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRoleViewModel @Inject constructor(
    private val roleUseCases: RoleUseCases,
    state: SavedStateHandle
): ViewModel() {

    var role = MutableStateFlow(TextFieldState(hint = "Enter Role"))
        private set

    var canWrite = MutableStateFlow(CheckBoxState(text = "Has Write access?", isChecked = false))
    private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val addRoleExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch {
            _eventFlow.emit(UiEvent.ShowError(throwable.message?:"Couldn't add role"))
        }
    }

    init {
        viewModelScope.launch {
            //Code here for any asy
        }
    }

    fun onEvent(event: AddRoleEvent) {
        when(event){
            is AddRoleEvent.EnteredRole -> {
                role.value = role.value.copy(text = event.role)
            }
            is AddRoleEvent.SelectedWriteAccess -> {
                canWrite.value = canWrite.value.copy(isChecked = event.canWrite)
            }
            is AddRoleEvent.AddRole -> {
                viewModelScope.launch(Dispatchers.IO + addRoleExceptionHandler) {
                    if(role.value.text.trim().isEmpty())
                        _eventFlow.emit(UiEvent.ShowError("Please enter role"))
                    else{
                        roleUseCases.addRoleUseCase(Role(roleId = null, name = role.value.text, canWrite = canWrite.value.isChecked))
                        _eventFlow.emit(UiEvent.RoleAdded)
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String): UiEvent()
        object RoleAdded: UiEvent()
    }
}