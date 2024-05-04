package com.arany.shg.feature_committee.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.Constants
import com.arany.shg.feature_dashboard.data.models.ServiceType
import com.arany.shg.feature_dashboard.data.models.Services
import com.arany.shg.feature_member.presentation.util.add_member.AddMemberEvent
import com.arany.shg.feature_member.presentation.util.add_member.AddMemberViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommitteeDetailsViewModel @Inject constructor(
    state: SavedStateHandle
): ViewModel() {

    private var committeeId: Int = state.get<Int>(Constants.NAV_ARG_COMMITTEE_ID)?: throw NullPointerException("Committee Id not received")

    var services = MutableStateFlow<List<Services>>(arrayListOf())
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        services.value = arrayListOf(
            Services(ServiceType.ADD_FINE, "Fine"),
            Services(ServiceType.ADD_ATTENDANCE, "Attendance"),
            Services(ServiceType.ADD_COMMITTEE, "Add Committee"),
            Services(ServiceType.ADD_MEMBER, "Member"),
            Services(ServiceType.ADD_ATTENDANCE, "Attendance"),
            Services(ServiceType.ADD_THRIFT, "Thrift"),
            Services(ServiceType.ADD_LOAN, "Loan"),
            Services(ServiceType.ADD_LOAN, "Loan"),
        )
    }

    fun onEvent(event: CommitteeDetailsEvent) {
        when (event) {
            is CommitteeDetailsEvent.ServiceCardClicked -> {
                viewModelScope.launch {
                    when(event.service.serviceType){
                        ServiceType.ADD_THRIFT ->{
                            _eventFlow.emit(UiEvent.NavigateToAddThriftScreen(committeeId))
                        }
                        ServiceType.ADD_FINE ->{
                            _eventFlow.emit(UiEvent.NavigateToAddFineScreen(committeeId))
                        }
                        ServiceType.ADD_COMMITTEE ->{
                            _eventFlow.emit(UiEvent.NavigateToAddCommitteeScreen(committeeId))
                        }
                        ServiceType.ADD_ATTENDANCE ->{
                            _eventFlow.emit(UiEvent.NavigateToAddAttendanceScreen(committeeId))
                        }
                        ServiceType.ADD_ROLE ->{
                            _eventFlow.emit(UiEvent.NavigateToAddRoleScreen(committeeId))
                        }
                        ServiceType.ADD_LOAN -> {
                            _eventFlow.emit(UiEvent.NavigateToAddLoanScreen(committeeId))
                        }
                        else -> {
                            //Do Nothing
                        }
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String): UiEvent()
        data class NavigateToAddThriftScreen(val committeeId: Int): UiEvent()
        data class NavigateToAddFineScreen(val committeeId: Int): UiEvent()
        data class NavigateToAddLoanScreen(val committeeId: Int): UiEvent()
        data class NavigateToAddMemberScreen(val committeeId: Int): UiEvent()
        data class NavigateToAddCommitteeScreen(val committeeId: Int): UiEvent()
        data class NavigateToAddAttendanceScreen(val committeeId: Int): UiEvent()
        data class NavigateToAddRoleScreen(val committeeId: Int): UiEvent()
    }
}