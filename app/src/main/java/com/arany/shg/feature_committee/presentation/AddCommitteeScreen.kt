package com.arany.shg.feature_committee.presentation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arany.shg.core.util.MemberState
import com.arany.shg.data.util.DateUtils
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_member.presentation.util.add_member.AddMemberEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddCommitteeScreen(navController: NavController, viewModel: AddCommitteeViewModel = hiltViewModel()) {
    val members by viewModel.members.collectAsState()
    val memberState by viewModel.member.collectAsState()
    val dateState by viewModel.date.collectAsState()
    val timeState by viewModel.time.collectAsState()
    val context = LocalContext.current

    var mDatePickerDialog: DatePickerDialog

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddCommitteeViewModel.UiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is AddCommitteeViewModel.UiEvent.CommitteeAdded -> {
                    Toast.makeText(context, "New committee Added with Id -> #${event.committeeId}", Toast.LENGTH_SHORT).show()
                    navController.navigateUp()
                    //navController.navigate(Screen.AttendanceScreen.route.plus("/1"))
                }
                is AddCommitteeViewModel.UiEvent.ShowDatePicker -> {
                    DatePickerDialog(context, { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                        viewModel.onEvent(AddCommitteeEvent.SelectedDate(DateUtils.convertToFormattedDate(mDayOfMonth, mMonth, mYear)))
                    }, event.year, event.month, event.day).show()
                }
                is AddCommitteeViewModel.UiEvent.ShowTimePicker -> {
                    TimePickerDialog(context, {_, mHour : Int, mMinute: Int ->
                        viewModel.onEvent(AddCommitteeEvent.SelectedTime(DateUtils.convertTo24HrsTo12HrsFormattedTime(mHour, mMinute)))
                    }, event.hour, event.minute, false).show()
                }
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 32.dp)) {

        MemberDropdownMenuBox(memberState = memberState, members = members, onClick = { viewModel.onEvent(
            AddCommitteeEvent.SelectedMember(it)) })

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = dateState.text,
            label = { Text(text = dateState.hint) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            onValueChange = {
                viewModel.onEvent(AddCommitteeEvent.SelectedDate(it))
            },
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = timeState.text,
            label = { Text(text = timeState.hint) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            onValueChange = {
                viewModel.onEvent(AddCommitteeEvent.SelectedTime(it))
            },
        )

        Spacer(modifier = Modifier.height(48.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            onClick = { viewModel.onEvent(AddCommitteeEvent.AddCommittee) },
        ) {
            Text(
                text = "Create Committee",
                style = MaterialTheme.typography.button
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MemberDropdownMenuBox(memberState: MemberState, members: List<Member>, onClick: (member: Member) -> Unit){
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            value = memberState.member?.name?:"",
            onValueChange = { },
            label = { Text(memberState.hint) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors()
        )
        ExposedDropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            members.forEach { iteratingMember ->
                DropdownMenuItem(
                    onClick = {
                        onClick(iteratingMember)
                        expanded = false
                    }
                ) {
                    Text(text = iteratingMember.name)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddCommittee_Preview() {
    MaterialTheme {
        //AddThriftScreen()
    }
}
