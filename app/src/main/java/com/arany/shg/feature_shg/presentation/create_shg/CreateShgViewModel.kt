package com.arany.shg.feature_shg.presentation.create_shg

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.feature_onboarding.data.model.InvalidLoginException
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import com.arany.shg.feature_shg.domain.use_case.SelfHelpGroupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: SelfHelpGroupEvent) {
        when(event){
            is SelfHelpGroupEvent.EnteredName -> {
                _shgName.value = _shgName.value.copy(text = event.shgName)
            }
            is SelfHelpGroupEvent.EnteredAddress -> {
                _address.value = _address.value.copy(text = event.address)
            }
            is SelfHelpGroupEvent.CreateSelfHelpGroup -> {
                Log.e("Login","ShgEvent.CreateSHG Triggered")
                viewModelScope.launch {
                    try {
                        selfHelpGroupUseCases.createSelfHelpGroupUseCase(SelfHelpGroup(name = shgName.value.text, address = address.value.text))
                        _eventFlow.emit(UiEvent.CreateSelfHelpGroupVerified)
                    }catch (e: InvalidLoginException){
                        Log.e("Login","LoginEvent.VerifyLogin Exception")
                        _eventFlow.emit(UiEvent.ShowSnackBarError(e.message?:"Couldn't create Self Help Group"))
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