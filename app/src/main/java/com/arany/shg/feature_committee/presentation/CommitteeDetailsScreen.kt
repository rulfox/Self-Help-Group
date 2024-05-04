package com.arany.shg.feature_committee.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.arany.shg.core.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CommitteeDetailsScreen(
    navController: NavHostController,
    viewModel: CommitteeDetailsViewModel = hiltViewModel(),
) {

    val services by viewModel.services.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is CommitteeDetailsViewModel.UiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is CommitteeDetailsViewModel.UiEvent.NavigateToAddThriftScreen -> {//
                    navController.navigate(Screen.AddThriftScreen.route.plus("/${event.committeeId}"))
                }
                is CommitteeDetailsViewModel.UiEvent.NavigateToAddFineScreen -> {//
                    navController.navigate(Screen.AddFineScreen.route.plus("/${event.committeeId}"))
                }
                is CommitteeDetailsViewModel.UiEvent.NavigateToAddLoanScreen -> {//
                    navController.navigate(Screen.AddLoanScreen.route.plus("/${event.committeeId}"))
                }
                is CommitteeDetailsViewModel.UiEvent.NavigateToAddMemberScreen -> {//
                    navController.navigate(Screen.AddMemberScreen.route.plus("/${event.committeeId}"))
                }
                is CommitteeDetailsViewModel.UiEvent.NavigateToAddCommitteeScreen -> {
                    navController.navigate(Screen.AddCommitteeScreen.route)
                }
                is CommitteeDetailsViewModel.UiEvent.NavigateToAddAttendanceScreen -> {//
                    navController.navigate(Screen.AttendanceScreen.route.plus("/${event.committeeId}"))
                }
                is CommitteeDetailsViewModel.UiEvent.NavigateToAddRoleScreen -> {

                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyVerticalGrid(columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
                    content = {
            itemsIndexed(services) { index, service ->
                Card(
                    shape = MaterialTheme.shapes.medium,
                    backgroundColor = MaterialTheme.colors.surface,
                    contentColor = contentColorFor(backgroundColor),
                    border = null,
                    elevation = 1.dp,
                    onClick = { viewModel.onEvent(CommitteeDetailsEvent.ServiceCardClicked(service)) }
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp),
                        text = service.name
                    )
                }
            }
        })
    }
}