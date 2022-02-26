package com.arany.shg.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arany.shg.R

@Composable
fun LoginPage(viewModel: LoginViewModel) {
    val phoneNumber by viewModel.phoneNumber.observeAsState("")
    val password by viewModel.password.observeAsState("")
    Column(verticalArrangement = Arrangement.Center) {
        HeaderImage()
        PhoneNumberInput(initialValue = phoneNumber, onValueChange = {viewModel.setPhoneNumber(it)})
        PhoneNumberInput(initialValue = password, onValueChange = {viewModel.setPassword(it)})
    }
}

@Composable
fun HeaderImage() {
    Image(
        painter = painterResource(id = R.drawable.login),
        contentDescription = "login header image",
        modifier = Modifier.height(100.dp)
    )
}

@Composable
fun PhoneNumberInput(initialValue: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(value = initialValue, onValueChange = onValueChange)
}

@Composable
fun PasswordInput(initialValue: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(value = initialValue, onValueChange = onValueChange)
}