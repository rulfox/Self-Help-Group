package com.arany.shg.feature_thrift.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddThriftScreen(navController: NavController, viewModel: AddThriftViewModel = hiltViewModel()) {

    val committeeState = viewModel.committee.value
    val memberState = viewModel.member.value
    val thriftAmountState = viewModel.thriftAmount.value

    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp)) {
        val options = listOf("Food", "Bill Payment", "Recharges", "Outing", "Other")

        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }

        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                value = committeeState.committee?.date?:"",
                onValueChange = { },
                label = { Text(committeeState.hint) },
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
                committeeState.listOfCommittees.forEach { iteratingCommittee ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.onEvent(AddThriftEvent.SelectedCommittee(iteratingCommittee))
                            expanded = false
                        }
                    ) {
                        Text(text = iteratingCommittee.committeeId.toString())
                    }
                }
            }
        }

        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
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
                memberState.listOfMembers.forEach { iteratingMember ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.onEvent(AddThriftEvent.SelectedMember(iteratingMember))
                            expanded = false
                        }
                    ) {
                        Text(text = iteratingMember.name)
                    }
                }
            }
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            value = thriftAmountState.text,
            label = { Text(text = thriftAmountState.hint) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            onValueChange = {
                viewModel.onEvent(AddThriftEvent.EnteredThriftAmount(it))
            },
        )

        Button(
            onClick = { viewModel.onEvent(AddThriftEvent.AddThrift) },
            modifier = Modifier.padding(top = 32.dp)) {
            Text(text = "Create")
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
