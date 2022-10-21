package com.arany.shg.feature_attendance.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.Constants
import com.arany.shg.feature_attendance.data.model.Attendance
import com.arany.shg.feature_attendance.data.model.AttendanceState
import com.arany.shg.feature_attendance.data.model.AttendanceStatus
import com.arany.shg.feature_attendance.domain.usecase.AttendanceUseCases
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import com.arany.shg.feature_member.presentation.util.add_member.AddMemberEvent
import com.arany.shg.feature_member.presentation.util.add_member.AddMemberViewModel
import com.arany.shg.feature_onboarding.presentation.login.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(private val useCases: AttendanceUseCases, private val memberUseCases: MemberUseCases): ViewModel() {
    var attendances: MutableList<Attendance> = arrayListOf()
        private set

    var attendanceStates = MutableStateFlow<List<AttendanceState>>(arrayListOf())
        private set

    private var committeeId: Int?= null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun generateAttendanceData(committeeId: Int){
        viewModelScope.launch {
            val calculatedAttendances = arrayListOf<AttendanceState>()
            memberUseCases.getMembersByShgIdUseCase(Constants.ShgId).collect{
                calculatedAttendances.clear()
                it.forEach { member ->
                    calculatedAttendances.add(AttendanceState(isPresent = false, leaveApplied = false, memberId = member.memberId!!, memberName = member.name, ))
                }
                attendanceStates.value = calculatedAttendances
            }
        }
    }

    fun onEvent(event: AttendanceEvent) {
        when (event) {
            is AttendanceEvent.SetCommitteeId -> {
                committeeId = event.committeeId
                generateAttendanceData(event.committeeId)
            }
            is AttendanceEvent.IsLeaveAppliedStatusChanged -> {
                attendanceStates.value = attendanceStates.value.apply {
                    this[event.index].leaveApplied = !this[event.index].leaveApplied
                }
            }
            is AttendanceEvent.IsPresentStatusChanged -> {
                attendanceStates.value = attendanceStates.value.apply {
                    this[event.index].isPresent = !this[event.index].isPresent
                }
            }
            is AttendanceEvent.AddAttendance -> {
                viewModelScope.launch {
                    var isNoOnePresent = true
                    for (attendanceState in attendances){
                        if(attendanceState.isPresent) {
                            isNoOnePresent = false
                            break
                        }
                    }
                    if(isNoOnePresent){
                        _eventFlow.emit(UiEvent.ShowError("No one is present in this committee"))
                    } else {
                        attendances.clear()
                        for (attendanceState in attendanceStates.value){
                            attendances.add(Attendance(committeeId = committeeId!!, memberId = attendanceState.memberId, isPresent = attendanceState.isPresent, leaveApplied = attendanceState.leaveApplied))
                        }
                        useCases.createAttendancesUseCase.invoke(attendances)
                        _eventFlow.emit(UiEvent.AttendanceAdded)
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowError(val message: String): UiEvent()
        object AttendanceAdded: UiEvent()
    }
}