package com.arany.shg.core.navigation

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable

sealed class Screen(
    val route: String,
    val title: String,
    val navIcon: (@Composable () -> Unit) = { Icon(Icons.Filled.Home, contentDescription = "home") },
    val objectName: String = "",
    val objectPath: String = ""
) {
    object DashboardScreen : Screen("dashboard_screen", "Dashboard")
    object MembersScreen : Screen("members_screen", "Members")
    object LoginScreen : Screen("login_screen", "Login")
    object CreateSelfHelpGroupScreen : Screen("create_self_help_group_screen", "Create SHG")
    object AddThriftScreen : Screen("add_thrift", "Thrift")
    object AddMemberScreen : Screen("add_member", "Member")
    object AddCommitteeScreen : Screen("add_committee", "Add Committee")
    object CommitteeListingScreen : Screen("committee_listing", "List Committees")
    object AttendanceScreen : Screen("attendance", "Attendance")
    object CommitteeDetailsScreen : Screen("committee_details", "Committee Details")
    object AddLoanScreen : Screen("add_loan", "Loan")
    object UnknownScreen: Screen("lost_screen", "Need Help")
    object AddRoleScreen: Screen("add_role_screen", "Add Role")
}