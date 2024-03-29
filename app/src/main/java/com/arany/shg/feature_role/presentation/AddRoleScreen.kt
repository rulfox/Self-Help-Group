package com.arany.shg.feature_role.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arany.shg.core.composables.Widgets.CheckBoxWidget
import com.arany.shg.core.util.MemberState
import com.arany.shg.feature_member.data.model.Member
import com.arany.shg.feature_onboarding.presentation.login.LoginEvent
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddRoleScreen(navController: NavController, viewModel: AddRoleViewModel = hiltViewModel()) {

    val roleState by viewModel.role.collectAsState()
    val canWriteState by viewModel.canWrite.collectAsState()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddRoleViewModel.UiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is AddRoleViewModel.UiEvent.RoleAdded -> {
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

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = roleState.text,
            label = { Text(text = roleState.hint) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            onValueChange = {
                viewModel.onEvent(AddRoleEvent.EnteredRole(it))
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
        )

        CheckBoxWidget(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            checkBoxState = canWriteState,
            onCheckedChangeListener = {
                viewModel.onEvent(AddRoleEvent.SelectedWriteAccess(it))
            },
        )

        Spacer(modifier = Modifier.height(48.dp))
        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            onClick = { viewModel.onEvent(AddRoleEvent.AddRole) },
        ) {
            Text(
                text = "Add Role",
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
