package com.arany.shg.feature_thrift.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arany.shg.core.composables.CustomAlertDialog
import com.arany.shg.core.util.MemberState
import com.arany.shg.feature_member.data.model.Member
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddThriftScreen(navController: NavController, viewModel: AddThriftViewModel = hiltViewModel()) {

    val committeeState by viewModel.committee.collectAsState()
    val memberState by viewModel.member.collectAsState()
    val thriftAmountState = viewModel.thriftAmount.collectAsState()
    val members by viewModel.members.collectAsState()
    val thriftAddedAlertDialogState by viewModel.thriftAddedAlertDialog.collectAsState()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddThriftViewModel.UiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is AddThriftViewModel.UiEvent.ThriftAdded -> {
                    Toast.makeText(context, "Thrift Added", Toast.LENGTH_SHORT).show()
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
                    AddThriftEvent.SelectedMember(it)
                )
            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = thriftAmountState.value.text,
            label = { Text(text = thriftAmountState.value.hint) },
            onValueChange = {
                viewModel.onEvent(AddThriftEvent.EnteredThriftAmount(it))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    viewModel.onEvent(AddThriftEvent.AddThrift)
                }
            )
        )

        Button(
            onClick = { viewModel.onEvent(AddThriftEvent.AddThrift) },
            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(), contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)) {
            Text(
                text = "Add Thrift",
                style = MaterialTheme.typography.button)
        }

        if(thriftAddedAlertDialogState.isShown) {
            CustomAlertDialog(
                title = thriftAddedAlertDialogState.title,
                description = thriftAddedAlertDialogState.description,
                onDismiss = { 
                    viewModel.onEvent(AddThriftEvent.HideThriftAddedAlertDialog)
                    focusManager.clearFocus()
                    viewModel.onEvent(AddThriftEvent.ClearScreen)
                }
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
fun AddThrift_Preview() {
    MaterialTheme {
        //AddThriftScreen()
    }
}
