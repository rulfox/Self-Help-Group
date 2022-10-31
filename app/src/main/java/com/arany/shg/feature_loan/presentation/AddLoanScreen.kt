package com.arany.shg.feature_loan.presentation

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
import com.arany.shg.feature_committee.presentation.AddCommitteeEvent
import com.arany.shg.feature_committee.presentation.AddCommitteeViewModel
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_thrift.data.model.ThriftStatus
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddLoanScreen(navController: NavController, viewModel: AddLoanViewModel = hiltViewModel()) {

    val memberState by viewModel.member.collectAsState()
    val loanAmountState = viewModel.loanAmount.collectAsState()
    val members by viewModel.members.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddLoanViewModel.UiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is AddLoanViewModel.UiEvent.LoanAdded -> {
                    Toast.makeText(context, "Loan Added", Toast.LENGTH_SHORT).show()
                    navController.navigateUp()
                    //navController.navigate(Screen.AttendanceScreen.route.plus("/1"))
                }
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp)) {

        MemberDropdownMenuBox(
            memberState = memberState,
            members = members,
            onClick = {
                viewModel.onEvent(
                    AddLoanEvent.SelectedMember(it)
                )
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = loanAmountState.value.text,
            label = { Text(text = loanAmountState.value.hint) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            onValueChange = {
                viewModel.onEvent(AddLoanEvent.EnteredLoanAmount(it))
            },
        )

        Button(
            onClick = { viewModel.onEvent(AddLoanEvent.AddLoan) },
            modifier = Modifier.padding(top = 32.dp).fillMaxWidth()) {
            Text(text = "Add Loan")
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
fun AddThrift_Preview() {
    MaterialTheme {
        //AddThriftScreen()
    }
}
