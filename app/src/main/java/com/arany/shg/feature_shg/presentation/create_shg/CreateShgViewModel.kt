package com.arany.shg.feature_shg.presentation.create_shg

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import com.arany.shg.feature_shg.domain.use_case.SelfHelpGroupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateShgViewModel @Inject constructor(
    private val selfHelpGroupUseCases: SelfHelpGroupUseCases
): ViewModel() {
    private var _shgName = mutableStateOf(ShgTextFieldState(hint = "Enter SHG name"))
    var shgName: State<ShgTextFieldState> = _shgName

    private var _address = mutableStateOf(ShgTextFieldState(hint = "Enter address"))
    var address: State<ShgTextFieldState> = _address

    private var _password = mutableStateOf(ShgTextFieldState(hint = "Enter password"))
    var password: State<ShgTextFieldState> = _password

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            _eventFlow.emit(UiEvent.ShowSnackBarError(throwable.message.toString()))
        }
    }

    fun onEvent(event: SelfHelpGroupEvent) {
        when(event){
            is SelfHelpGroupEvent.EnteredName -> {
                _shgName.value = _shgName.value.copy(text = event.shgName)
            }
            is SelfHelpGroupEvent.EnteredAddress -> {
                _address.value = _address.value.copy(text = event.address)
            }
            is SelfHelpGroupEvent.EnteredPassword -> {
                _password.value = _password.value.copy(text = event.password)
            }
            is SelfHelpGroupEvent.CreateSelfHelpGroup -> {
                viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
                    if(_shgName.value.text.isEmpty())
                        _eventFlow.emit(UiEvent.ShowSnackBarError("Please enter name"))
                    else if(_address.value.text.isEmpty())
                        _eventFlow.emit(UiEvent.ShowSnackBarError("Please enter address"))
                    else if(_password.value.text.isEmpty())
                        _eventFlow.emit(UiEvent.ShowSnackBarError("Please enter password"))
                    else {
                        selfHelpGroupUseCases.createSelfHelpGroupUseCase(SelfHelpGroup(name = shgName.value.text, address = address.value.text, password = password.value.text))
                        _eventFlow.emit(UiEvent.CreateSelfHelpGroupVerified)
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackBarError(val message: String): UiEvent()
        object CreateSelfHelpGroupVerified: UiEvent()
    }
}