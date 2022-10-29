package com.arany.shg.feature_thrift.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.CommitteeState
import com.arany.shg.core.util.Constants
import com.arany.shg.core.util.MemberState
import com.arany.shg.core.util.TextFieldState
import com.arany.shg.data.models.Committee
import com.arany.shg.data.util.DateUtils
import com.arany.shg.data.util.DateUtils.toFormattedString
import com.arany.shg.data.util.DateUtils.toString
import com.arany.shg.feature_committee.domain.usecase.CommitteeUseCases
import com.arany.shg.feature_committee.presentation.AddCommitteeViewModel
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_shg.domain.use_case.SelfHelpGroupUseCases
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
class AddThriftViewModel @Inject constructor(
    private val memberUseCases: MemberUseCases,
    private val thriftUseCases: ThriftUseCases,
    state: SavedStateHandle
): ViewModel() {

    private var committeeId: Int = state.get<Int>(Constants.NAV_ARG_COMMITTEE_ID)!!

    var committees = MutableStateFlow<List<Committee>>(arrayListOf())
        private set

    var members = MutableStateFlow<List<Member>>(arrayListOf())
        private set

    var thriftAmount = MutableStateFlow(TextFieldState(hint = "Enter Thrift Amount"))
        private set

    var committee = MutableStateFlow(CommitteeState(hint = "Select Committee"))
    private set

    var member = MutableStateFlow(MemberState(hint = "Select Member"))
    private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val addThriftExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch {
            _eventFlow.emit(UiEvent.ShowError(throwable.message?:"Couldn't add Thrift"))
        }
    }

    init {
        viewModelScope.launch {
            memberUseCases.getMembersByShgIdUseCase(Constants.ShgId).collect{
                it.forEach {
                    Log.e("Members", it.toString())
                }
                members.value = it
            }
        }
    }

    fun onEvent(event: AddThriftEvent) {
        when(event){
            is AddThriftEvent.EnteredThriftAmount -> {
                thriftAmount.value = thriftAmount.value.copy(text = event.amount)
            }
            is AddThriftEvent.SelectedCommittee -> {
                committee.value = committee.value.copy(committee = event.committee)
            }
            is AddThriftEvent.SelectedMember -> {
                member.value = member.value.copy(member = event.member)
            }
            is AddThriftEvent.AddThrift -> {
                viewModelScope.launch(Dispatchers.IO + addThriftExceptionHandler) {
                    if(null == member.value.member)
                        _eventFlow.emit(UiEvent.ShowError("Select member"))
                    else if(thriftAmount.value.text.isEmpty())
                        _eventFlow.emit(UiEvent.ShowError("Enter thrift"))
                    else{
                        thriftUseCases.addThriftUseCase(Thrift(thriftId = null, committeeId = committeeId, memberId = member.value.member?.memberId, amount = thriftAmount.value.text.toDoubleOrNull(), dateTime = DateUtils.getCurrentDateTime().toFormattedString()))
                        _eventFlow.emit(UiEvent.ThriftAdded)
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String): UiEvent()
        object ThriftAdded: UiEvent()
    }
}