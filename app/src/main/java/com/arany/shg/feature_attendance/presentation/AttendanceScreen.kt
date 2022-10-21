package com.arany.shg.feature_attendance.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AttendanceScreen (committeeId: Int, navController: NavController, viewModel: AttendanceViewModel = hiltViewModel()){

    val attendances by viewModel.attendanceStates.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(context) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is AttendanceViewModel.UiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()

                }
                is AttendanceViewModel.UiEvent.AttendanceAdded -> {
                    navController.navigateUp()
                }
            }
        }
        viewModel.onEvent(AttendanceEvent.SetCommitteeId(committeeId))
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp)) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(attendances.size) { index ->
                AttendanceCheckBox(attendanceState = attendances[index], modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), onAttendanceStatusChangeListener = { viewModel.onEvent(AttendanceEvent.IsPresentStatusChanged(index, it)) }, onLeaveAppliedStatusChangeListener = { viewModel.onEvent(AttendanceEvent.IsLeaveAppliedStatusChanged(index, it)) })
            }
        }

    }
}