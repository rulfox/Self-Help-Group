package com.arany.shg.feature_dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class DashboardViewModel @Inject constructor(

): ViewModel() {
    var services = MutableStateFlow<List<Services>>(arrayListOf())
        private set

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        services.value = arrayListOf(
            Services(ServiceType.LIST_COMMITTEES, "List Committees"),
            Services(ServiceType.ADD_COMMITTEE, "Add Committee"),
            Services(ServiceType.ADD_MEMBER, "Member"),
        )
    }

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.ServiceCardClicked -> {
                viewModelScope.launch {
                    when(event.service.serviceType){
                        ServiceType.LIST_COMMITTEES ->{
                            _eventFlow.emit(UiEvent.NavigateToCommitteeListingScreen(event.service))
                        }
                        ServiceType.ADD_MEMBER ->{
                            _eventFlow.emit(UiEvent.NavigateToAddMemberScreen(event.service))
                        }
                        ServiceType.ADD_COMMITTEE ->{
                            _eventFlow.emit(UiEvent.NavigateToAddCommitteeScreen(event.service))
                        }
                        ServiceType.ADD_ROLE ->{
                            _eventFlow.emit(UiEvent.NavigateToAddRoleScreen(event.service))
                        }
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String): UiEvent()
        data class NavigateToAddThriftScreen(val services: Services): UiEvent()
        data class NavigateToAddFineScreen(val services: Services): UiEvent()
        data class NavigateToAddMemberScreen(val services: Services): UiEvent()
        data class NavigateToAddCommitteeScreen(val services: Services): UiEvent()
        data class NavigateToAddAttendanceScreen(val services: Services): UiEvent()
        data class NavigateToAddRoleScreen(val services: Services): UiEvent()
        data class NavigateToCommitteeListingScreen(val services: Services): UiEvent()
    }
}