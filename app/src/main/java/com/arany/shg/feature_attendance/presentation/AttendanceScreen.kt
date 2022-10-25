package com.arany.shg.feature_attendance.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
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
fun AttendanceScreen (navController: NavController, viewModel: AttendanceViewModel = hiltViewModel()){
    val attendances by viewModel.attendanceStates.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(true) {
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
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp)) {

        LazyColumn(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
            itemsIndexed(attendances){index, item ->
                AttendanceCheckBox(attendanceState = item, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), onAttendanceStatusChangeListener = { viewModel.onEvent(AttendanceEvent.IsPresentStatusChanged(index, it)) }, onLeaveAppliedStatusChangeListener = { viewModel.onEvent(AttendanceEvent.IsLeaveAppliedStatusChanged(index, it)) })
            }
        }

        Button(
            onClick = { viewModel.onEvent(AttendanceEvent.AddAttendance) },
            modifier = Modifier.fillMaxWidth().padding(top = 32.dp)) {
            Text(text = "Add Attendance")
        }
    }
}