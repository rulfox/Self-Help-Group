package com.arany.shg.feature_thrift.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.CommitteeState
import com.arany.shg.core.util.MemberState
import com.arany.shg.core.util.TextFieldState
import com.arany.shg.data.models.Committee
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_shg.domain.use_case.SelfHelpGroupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddThriftViewModel @Inject constructor(
    private val useCases: MemberUseCases,
    private val selfHelpGroupUseCases: SelfHelpGroupUseCases,
): ViewModel() {

    private var committees: List<Committee> = arrayListOf()
    private var members: List<Member> = arrayListOf()

    private var _thriftAmount = mutableStateOf(TextFieldState(hint = "Enter Thrift Amount"))
    var thriftAmount: State<TextFieldState> = _thriftAmount

    private var _committee = mutableStateOf(CommitteeState(hint = "Select Committee"))
    var committee: State<CommitteeState> = _committee

    private var _member = mutableStateOf(MemberState(hint = "Select Member"))
    var member: State<MemberState> = _member

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        //TODO
    }

    fun onEvent(event: AddThriftEvent) {
        when(event){
            is AddThriftEvent.EnteredThriftAmount -> {
                _thriftAmount.value = _thriftAmount.value.copy(text = event.amount)
            }
            is AddThriftEvent.SelectedCommittee -> {
                _committee.value = _committee.value.copy(committee = event.committee)
            }
            is AddThriftEvent.SelectedMember -> {
                _member.value = _member.value.copy(member = event.member)
            }
            is AddThriftEvent.AddThrift -> {
                Log.e("Login","ShgEvent.CreateSHG Triggered")
                viewModelScope.launch {
                    /*try {
                        selfHelpGroupUseCases.createSelfHelpGroupUseCase(SelfHelpGroup(name = shgName.value.text, address = address.value.text))
                        _eventFlow.emit(UiEvent.CreateSelfHelpGroupVerified)
                    }catch (e: InvalidLoginException){
                        Log.e("Login","LoginEvent.VerifyLogin Exception")
                        _eventFlow.emit(UiEvent.ShowSnackBarError(e.message?:"Couldn't create Self Help Group"))
                    }*/
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String): UiEvent()
        object ThriftAdded: UiEvent()
    }
}