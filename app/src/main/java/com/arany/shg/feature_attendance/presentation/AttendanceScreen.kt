package com.arany.shg.feature_attendance.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
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
        .fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(top = 24.dp).weight(1f), verticalArrangement = Arrangement.spacedBy(16.dp), contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp)) {
            itemsIndexed(attendances){index, item ->
                AttendanceCheckBox(attendanceState = item, modifier = Modifier.fillMaxWidth(), onAttendanceStatusChangeListener = { viewModel.onEvent(AttendanceEvent.IsPresentStatusChanged(index, it)) }, onLeaveAppliedStatusChangeListener = { viewModel.onEvent(AttendanceEvent.IsLeaveAppliedStatusChanged(index, it)) })
            }
        }
        Button(
            onClick = { viewModel.onEvent(AttendanceEvent.AddAttendance) },
            modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)) {
            Text(
                text = "Add Attendance",
                style = MaterialTheme.typography.button
            )
        }
    }
}