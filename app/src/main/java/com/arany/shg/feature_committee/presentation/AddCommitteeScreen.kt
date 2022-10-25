package com.arany.shg.feature_committee.presentation

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
import com.arany.shg.core.util.Constants
import com.arany.shg.core.util.MemberState
import com.arany.shg.core.util.Screen
import com.arany.shg.feature_member.data.model.Member
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddCommitteeScreen(navController: NavController, viewModel: AddCommitteeViewModel = hiltViewModel()) {
    val members by viewModel.members.collectAsState()
    val memberState by viewModel.member.collectAsState()
    val dateState by viewModel.date.collectAsState()
    val timeState by viewModel.time.collectAsState()
    val context = LocalContext.current

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
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp)) {

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

        Button(
            onClick = { viewModel.onEvent(AddCommitteeEvent.AddCommittee) },
            modifier = Modifier.fillMaxWidth().padding(top = 32.dp)) {
            Text(text = "Create Committee")
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
        TextField(
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
            colors = ExposedDropdownMenuDefaults.textFieldColors()
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
