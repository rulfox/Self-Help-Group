package com.arany.shg.feature_fine.presentation

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
import com.arany.shg.core.util.FineTypeState
import com.arany.shg.core.util.MemberState
import com.arany.shg.feature_committee.presentation.AddCommitteeEvent
import com.arany.shg.feature_committee.presentation.AddCommitteeViewModel
import com.arany.shg.feature_fine.data.models.FineType
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_thrift.data.model.ThriftStatus
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddFineScreen(navController: NavController, viewModel: AddFineViewModel = hiltViewModel()) {

    val memberState by viewModel.member.collectAsState()
    val members by viewModel.members.collectAsState()

    val fineTypeState by viewModel.fineType.collectAsState()
    val fineTypes by viewModel.fineTypes.collectAsState()

    val loanAmountState = viewModel.fineAmount.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddFineViewModel.UiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is AddFineViewModel.UiEvent.LoanAdded -> {
                    Toast.makeText(context, "Fine Added", Toast.LENGTH_SHORT).show()
                    navController.navigateUp()
                }
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp)) {

        MemberDropdownMenuBox(
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            memberState = memberState,
            members = members,
            onClick = {
                viewModel.onEvent(
                    AddFineEvent.SelectedMember(it)
                )
            }
        )

        FineTypeDropdownMenuBox(
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            fineTypeState = fineTypeState,
            fineTypes = fineTypes,
            onClick = {
                viewModel.onEvent(
                    AddFineEvent.SelectedFineType(it)
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
                viewModel.onEvent(AddFineEvent.EnteredFineAmount(it))
            },
        )

        Button(
            onClick = { viewModel.onEvent(AddFineEvent.AddFine) },
            modifier = Modifier.padding(top = 32.dp).fillMaxWidth(), contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),) {
            Text(
                text = "Add Fine",
                style = MaterialTheme.typography.button
            )
        }

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MemberDropdownMenuBox(modifier: Modifier, memberState: MemberState, members: List<Member>, onClick: (member: Member) -> Unit){
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        modifier = modifier,
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FineTypeDropdownMenuBox(modifier: Modifier, fineTypeState: FineTypeState, fineTypes: List<FineType>, onClick: (fineType: FineType) -> Unit){
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
            value = fineTypeState.fineType?.name?:"",
            onValueChange = { },
            label = { Text(fineTypeState.hint) },
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
            fineTypes.forEach { iteratingFineType ->
                DropdownMenuItem(
                    onClick = {
                        onClick(iteratingFineType)
                        expanded = false
                    }
                ) {
                    Text(text = iteratingFineType.name)
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
