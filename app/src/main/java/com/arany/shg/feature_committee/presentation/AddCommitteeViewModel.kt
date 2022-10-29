package com.arany.shg.feature_committee.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.CommitteeState
import com.arany.shg.core.util.Constants
import com.arany.shg.core.util.MemberState
import com.arany.shg.core.util.TextFieldState
import com.arany.shg.data.models.Committee
import com.arany.shg.data.util.DateUtils.getCurrentDateOnly
import com.arany.shg.data.util.DateUtils.getCurrentTimeOnly
import com.arany.shg.feature_committee.domain.repository.CommitteeRepository
import com.arany.shg.feature_committee.domain.usecase.CommitteeUseCases
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.domain.repository.MemberRepository
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_shg.domain.use_case.SelfHelpGroupUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCommitteeViewModel @Inject constructor(
    private val committeeUseCases: CommitteeUseCases,
    private val memberUseCases: MemberUseCases,
): ViewModel() {

    var members = MutableStateFlow<List<Member>>(arrayListOf())
        private set

    var member = MutableStateFlow(MemberState(hint = "Select Member"))
        private set

    var date = MutableStateFlow(TextFieldState(text = getCurrentDateOnly(), hint = "Enter date"))
        private set

    var time = MutableStateFlow(TextFieldState(text = getCurrentTimeOnly(), hint = "Enter time"))
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            memberUseCases.getMembersByShgIdUseCase(Constants.ShgId).collect{
                members.value = it
            }
        }
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        viewModelScope.launch {
            _eventFlow.emit(UiEvent.ShowError(throwable.message.toString()))
        }
    }

    fun onEvent(event: AddCommitteeEvent) {
        when(event){
            is AddCommitteeEvent.SelectedDate -> {
                date.value = date.value.copy(text = event.date)
            }
            is AddCommitteeEvent.SelectedTime -> {
                time.value = date.value.copy(text = event.time)
            }
            is AddCommitteeEvent.SelectedMember -> {
                member.value = member.value.copy(member = event.member)
            }
            is AddCommitteeEvent.AddCommittee -> {
                viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
                    if(null == member.value.member){
                        _eventFlow.emit(UiEvent.ShowError("Select member"))
                    } else if(date.value.text.isBlank()){
                        _eventFlow.emit(UiEvent.ShowError("Select date"))
                    } else if(time.value.text.isBlank()){
                        _eventFlow.emit(UiEvent.ShowError("Select time"))
                    } else {
                        val committeeId = committeeUseCases.createCommitteeUseCase(Committee(shgId = Constants.ShgId, memberId = member.value.member?.memberId!!, date = "${date.value.text} ${time.value.text}"))
                        _eventFlow.emit(UiEvent.CommitteeAdded(committeeId))
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String): UiEvent()
        data class CommitteeAdded(val committeeId: Long): UiEvent()
        data class ShowDatePicker(val day: Int, val month: Int, val year: Int): UiEvent()
        data class ShowTimePicker(val hour: Int, val minute: Int): UiEvent()
    }
}