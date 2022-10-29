package com.arany.shg.feature_committee.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arany.shg.core.util.Screen
import com.arany.shg.data.util.DateUtils
import com.arany.shg.data.util.DateUtils.getDateOnly
import com.arany.shg.data.util.DateUtils.getTimeOnly
import com.arany.shg.data.util.DateUtils.toDate
import com.arany.shg.feature_committee.data.model.CommitteeWithDetails
import com.arany.shg.ui.theme.SelfHelpGroupTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CommitteeListingScreen(navController: NavController, viewModel: CommitteeListingViewModel = hiltViewModel()) {
    val committeeWithDetailsState by viewModel.committeeWithDetails.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is CommitteeListingViewModel.UiEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is CommitteeListingViewModel.UiEvent.NavigateToCommitteeDetailsScreen -> {
                    Log.e("Navigation","Trigerred")
                    navController.navigate(Screen.CommitteeDetailsScreen.route.plus("/${event.committeeWithDetails.committee.committeeId}"))
                }
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
            itemsIndexed(committeeWithDetailsState){index, item ->
                CommitteeCard(
                    committeeWithDetails = item,
                    modifier = Modifier.fillMaxWidth().padding(15.dp),
                    onCardClicked = {
                        viewModel.onEvent(CommitteeListingEvent.CommitteeCardClicked(it))
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CommitteeCard(committeeWithDetails: CommitteeWithDetails, modifier: Modifier, onCardClicked: (committeeWithDetails: CommitteeWithDetails) -> Unit) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = 1.dp,
        onClick = { onCardClicked(committeeWithDetails) }
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)) {
            Text(
                text = "${committeeWithDetails.member.name}'s House",
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            DateUtils.getCurrentDateOnly()
            Text(
                text = committeeWithDetails.committee.date?.toDate()?.getDateOnly()?:"UnKnown",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = committeeWithDetails.committee.date?.toDate()?.getTimeOnly()?:"UnKnown",
                textAlign = TextAlign.Start,
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SelfHelpGroupTheme {

    }
}