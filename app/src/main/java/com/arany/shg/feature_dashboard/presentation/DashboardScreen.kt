package com.arany.shg.feature_dashboard.presentation

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
import com.arany.shg.core.util.Constants
import com.arany.shg.core.util.Screen
import com.arany.shg.feature_committee.presentation.CommitteeListingViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: DashboardViewModel = hiltViewModel(),
) {

    val services by viewModel.services.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is DashboardViewModel.UiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is DashboardViewModel.UiEvent.NavigateToCommitteeListingScreen -> {
                    navController.navigate(Screen.CommitteeListingScreen.route)
                }
                is DashboardViewModel.UiEvent.NavigateToAddThriftScreen -> {//
                    navController.navigate(Screen.AddThriftScreen.route)
                }
                is DashboardViewModel.UiEvent.NavigateToAddFineScreen -> {//

                }
                is DashboardViewModel.UiEvent.NavigateToAddMemberScreen -> {//
                    navController.navigate(Screen.AddMemberScreen.route)
                }
                is DashboardViewModel.UiEvent.NavigateToAddCommitteeScreen -> {
                    navController.navigate(Screen.AddCommitteeScreen.route)
                }
                is DashboardViewModel.UiEvent.NavigateToAddAttendanceScreen -> {//
                    navController.navigate(Screen.AttendanceScreen.route)
                }
                is DashboardViewModel.UiEvent.NavigateToAddRoleScreen -> {

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
                    onClick = { viewModel.onEvent(DashboardEvent.ServiceCardClicked(service)) }
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