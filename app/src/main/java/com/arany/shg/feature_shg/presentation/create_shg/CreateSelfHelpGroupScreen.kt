package com.arany.shg.feature_shg.presentation.create_shg

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arany.shg.R
import com.arany.shg.core.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CreateSelfHelpGroupScreen(navController: NavController, viewModel: CreateShgViewModel = hiltViewModel()){

    val shgNameState = viewModel.shgName.value
    val addressState = viewModel.address.value

    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is CreateShgViewModel.UiEvent.ShowSnackBarError -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is CreateShgViewModel.UiEvent.CreateSelfHelpGroupVerified -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Get Help")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.login),
                contentDescription = "Login image banner",
                modifier = Modifier.height(250.dp)
            )
            OutlinedTextField(
                value = shgNameState.text,
                label = { Text(text = "Enter Self Help Group Name") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = {
                    viewModel.onEvent(SelfHelpGroupEvent.EnteredName(it))
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = addressState.text,
                label = { Text(text = "Enter Address") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = {
                    viewModel.onEvent(SelfHelpGroupEvent.EnteredAddress(it))
                },
            )
            Button(
                onClick = { viewModel.onEvent(SelfHelpGroupEvent.CreateSelfHelpGroup) },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(text = "Create")
            }
        }
    }
}