package com.arany.shg.feature_attendance.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arany.shg.core.util.Constants
import com.arany.shg.feature_attendance.data.model.Attendance
import com.arany.shg.feature_attendance.data.model.AttendanceState
import com.arany.shg.feature_attendance.domain.usecase.AttendanceUseCases
import com.arany.shg.feature_member.domain.use_case.MemberUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val useCases: AttendanceUseCases,
    private val memberUseCases: MemberUseCases,
    state: SavedStateHandle): ViewModel() {

    var attendances: MutableList<Attendance> = arrayListOf()
        private set

    var attendanceStates = MutableStateFlow<SnapshotStateList<AttendanceState>>(mutableStateListOf())
        private set

    private var committeeId: Int? = state.get<Int>(Constants.NAV_ARG_COMMITTEE_ID)

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        generateAttendanceData()
    }

    private fun generateAttendanceData(){
        viewModelScope.launch {
            val calculatedAttendances = mutableStateListOf<AttendanceState>()
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
                //committeeId = event.committeeId
            }
            is AttendanceEvent.IsLeaveAppliedStatusChanged -> {
                attendanceStates.update { list ->
                    list[event.index] = list[event.index].copy(leaveApplied = event.hasApplied)
                    list
                }
            }
            is AttendanceEvent.IsPresentStatusChanged -> {
                attendanceStates.update { list ->
                    list[event.index] = list[event.index].copy(isPresent = event.isPresent)
                    list
                }
            }
            is AttendanceEvent.AddAttendance -> {
                viewModelScope.launch {
                    var presentCount = 0
                    for (attendanceState in attendanceStates.value){
                        if(attendanceState.isPresent) {
                            presentCount++
                            break
                        }
                    }
                    if(presentCount == 0){
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