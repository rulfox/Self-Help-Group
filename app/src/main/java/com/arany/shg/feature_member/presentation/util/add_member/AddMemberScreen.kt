package com.arany.shg.feature_member.presentation.util.add_member

import android.util.Log
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
import com.arany.shg.core.util.RoleState
import com.arany.shg.data.models.Role

@Composable
fun AddMemberScreen(navController: NavController, viewModel: AddMemberViewModel = hiltViewModel()){
    val rolesState by viewModel.roles.collectAsState()
    val roleState = viewModel.role.value
    val nameState = viewModel.name.value
    val addressState = viewModel.address.value
    val phoneNumberState = viewModel.phoneNumber.value
    val emailState = viewModel.email.value
    val passwordState = viewModel.password.value

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp)) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = nameState.text,
            label = { Text(text = nameState.hint) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = {
                viewModel.onEvent(AddMemberEvent.EnteredName(it))
            },
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = addressState.text,
            label = { Text(text = addressState.hint) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = {
                viewModel.onEvent(AddMemberEvent.EnteredAddress(it))
            },
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = phoneNumberState.text,
            label = { Text(text = phoneNumberState.hint) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            onValueChange = {
                viewModel.onEvent(AddMemberEvent.EnteredPhoneNumber(it))
            },
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = emailState.text,
            label = { Text(text = emailState.hint) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            onValueChange = {
                viewModel.onEvent(AddMemberEvent.EnteredEmail(it))
            },
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            value = passwordState.text,
            label = { Text(text = passwordState.hint) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {
                viewModel.onEvent(AddMemberEvent.EnteredPassword(it))
            },
        )

        RoleDropdownMenuBox(roleState, rolesState, onClick = { role ->
            viewModel.onEvent(AddMemberEvent.SelectedRole(role))
        })

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoleDropdownMenuBox(roleState: RoleState, rolesState: List<Role>, onClick: (role: Role) -> Unit){
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
            value = roleState.role?.name?:"",
            onValueChange = { },
            label = { Text(roleState.hint) },
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
            Log.e("Recomposition"," In ExposedDropDownMenu Roles Size "+roleState.listOfRoles.size)
            rolesState.forEach { iteratingRole ->
                Log.e("Roles",iteratingRole.toString())
            }
            rolesState.forEach { iteratingRole ->
                DropdownMenuItem(
                    onClick = {
                        onClick(iteratingRole)
                        expanded = false
                    }
                ) {
                    Text(text = iteratingRole.name?:"")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddMember_Preview() {
    MaterialTheme {
        //AddMemberScreen()
    }
}