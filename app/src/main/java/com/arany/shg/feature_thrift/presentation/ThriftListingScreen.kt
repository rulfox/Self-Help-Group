package com.arany.shg.feature_thrift.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.arany.shg.core.util.CommitteeBasedListWidget
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ThriftListingScreen(navController: NavController, viewModel: ThriftListingViewModel = hiltViewModel()) {

    val committeeState by viewModel.committeesWithDetails.collectAsState()
    val thriftAddedAlertDialogState by viewModel.thriftAddedAlertDialog.collectAsState()
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is ThriftListingViewModel.UiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

                is ThriftListingViewModel.UiEvent.ThriftAdded -> {
                    Toast.makeText(context, "Thrift Added", Toast.LENGTH_SHORT).show()
                    navController.navigateUp()
                }
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        itemsIndexed(committeeState) { index, item ->
            CommitteeBasedListWidget()
        }
    }

    if (thriftAddedAlertDialogState.isShown) {
        CustomAlertDialog(
            title = thriftAddedAlertDialogState.title,
            description = thriftAddedAlertDialogState.description,
            onDismiss = {
                viewModel.onEvent(ViewThriftEvent.HideThriftErrorAlertDialog)
                focusManager.clearFocus()
                viewModel.onEvent(ViewThriftEvent.RefreshThrifts)
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ViewThriftScreen_Preview() {
    MaterialTheme {
        //AddThriftScreen()
    }
}
