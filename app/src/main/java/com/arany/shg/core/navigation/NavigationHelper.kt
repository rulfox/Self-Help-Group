package com.arany.shg.core.navigation

import android.view.MenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arany.shg.R
import com.arany.shg.ui.theme.Purple500

object NavigationHelper {

    @Composable
    fun currentScreen(navController: NavController): Screen {
        return when (currentRoute(navController)) {
            Screen.DashboardScreen.route -> Screen.DashboardScreen
            Screen.MembersScreen.route -> Screen.MembersScreen
            Screen.LoginScreen.route -> Screen.LoginScreen
            Screen.CreateSelfHelpGroupScreen.route -> Screen.CreateSelfHelpGroupScreen
            Screen.AddThriftScreen.route -> Screen.AddThriftScreen
            Screen.AddMemberScreen.route -> Screen.AddMemberScreen
            Screen.AddCommitteeScreen.route -> Screen.AddCommitteeScreen
            Screen.CommitteeListingScreen.route -> Screen.CommitteeListingScreen
            Screen.AttendanceScreen.route -> Screen.AttendanceScreen
            Screen.CommitteeDetailsScreen.route -> Screen.CommitteeDetailsScreen
            Screen.AddLoanScreen.route -> Screen.AddLoanScreen
            Screen.AddRoleScreen.route -> Screen.AddRoleScreen
            Screen.AddFineScreen.route -> Screen.AddFineScreen
            else -> {
                Screen.UnknownScreen
            }
        }

    }

    @Composable
    fun currentRoute(navController: NavController): String? {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
    }

    @Composable
    fun navigationTitle(navController: NavController): String {
        return when (currentRoute(navController)) {
            Screen.DashboardScreen.route -> Screen.DashboardScreen.title
            Screen.MembersScreen.route -> Screen.MembersScreen.title
            Screen.LoginScreen.route -> Screen.LoginScreen.title
            Screen.CreateSelfHelpGroupScreen.route -> Screen.CreateSelfHelpGroupScreen.title
            Screen.AddThriftScreen.route -> Screen.AddThriftScreen.title
            Screen.AddMemberScreen.route -> Screen.AddMemberScreen.title
            Screen.AddCommitteeScreen.route -> Screen.AddCommitteeScreen.title
            Screen.CommitteeListingScreen.route -> Screen.CommitteeListingScreen.title
            Screen.AttendanceScreen.route -> Screen.AttendanceScreen.title
            Screen.CommitteeDetailsScreen.route -> Screen.CommitteeDetailsScreen.title
            Screen.AddLoanScreen.route -> Screen.AddLoanScreen.title
            Screen.AddRoleScreen.route -> Screen.AddRoleScreen.title
            Screen.AddFineScreen.route -> Screen.AddFineScreen.title
            else -> {
                ""
            }
        }
    }

    @Composable
    fun AppBarWithArrow(
        title: String?,
        pressOnBack: () -> Unit
    ) {
        TopAppBar(
            elevation = 6.dp,
            backgroundColor = Purple500,
            modifier = Modifier.height(58.dp)
        ) {
            Row {
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    imageVector = Icons.Filled.ArrowBack,
                    colorFilter = ColorFilter.tint(Color.White),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable {
                            pressOnBack()
                        }
                )

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically),
                    text = title ?: "",
                    style = MaterialTheme.typography.h6,
                    color = Color.White
                )
            }
        }
    }

    @Composable
    fun HomeAppBar(title: String, openDrawer: () -> Unit, openFilters: () -> Unit) {
        TopAppBar(
            backgroundColor = Purple500,
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6,
                )
            },
            navigationIcon = {
                IconButton(onClick = {
                    openDrawer()
                }) {
                    Icon(Icons.Default.Menu, "Menu")
                }
            },
            /*actions = {
                IconButton(onClick = openFilters) {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                }
            }*/
        )
    }

    fun getNavigationItems(): List<Screen>{
        return listOf(
            Screen.DashboardScreen,
            Screen.MembersScreen,
            Screen.AddThriftScreen,
            Screen.AddMemberScreen,
            Screen.AddCommitteeScreen,
            Screen.CommitteeListingScreen,
            Screen.AttendanceScreen,
            Screen.CommitteeDetailsScreen,
            Screen.AddLoanScreen,
            Screen.AddRoleScreen,
            Screen.AddFineScreen,
        )
    }
}