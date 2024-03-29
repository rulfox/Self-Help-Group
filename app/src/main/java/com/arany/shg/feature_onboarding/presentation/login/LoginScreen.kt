package com.arany.shg.feature_onboarding.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arany.shg.R
import com.arany.shg.core.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()){

    val phoneNumberState = viewModel.phoneNumber.value
    val passwordState = viewModel.password.value

    val scaffoldState = rememberScaffoldState()
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is LoginViewModel.UiEvent.ShowSnackBarError -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is LoginViewModel.UiEvent.LoginVerified -> {
                    navController.navigate(Screen.DashboardScreen.route)
                }
                is LoginViewModel.UiEvent.CreateSelfHelpGroup -> {
                    navController.navigate(Screen.CreateSelfHelpGroupScreen.route)
                }
            }
        }
    }

    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.CreateSelfHelpGroupScreen.route)
                },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Get Help")
            }
        },
        scaffoldState = scaffoldState
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.login),
                contentDescription = "Login image banner",
                modifier = Modifier.height(250.dp)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                value = phoneNumberState.text,
                label = { Text(text = "Enter Your SHG") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredPhoneNumber(it))
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                value = passwordState.text,
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(text = "Enter Your Password") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                onValueChange = {
                    viewModel.onEvent(LoginEvent.EnteredPassword(it))
                },
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        viewModel.onEvent(LoginEvent.VerifyLogin)
                    }
                )
            )
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                onClick = { viewModel.onEvent(LoginEvent.VerifyLogin) },
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.button
                )
            }
        }
    }
}