package com.arany.shg.core.util

sealed class Screen(val route: String) {
    object DashboardScreen: Screen("dashboard_screen")
    object MembersScreen: Screen("members_screen")
    object LoginScreen: Screen("login_screen")
    object CreateSelfHelpGroupScreen: Screen("create_self_help_group_screen")
    object AddThriftScreen: Screen("add_thrift")
    object AddMemberScreen: Screen("add_member")
    object AddCommitteeScreen: Screen("add_committee")
    object CommitteeListingScreen: Screen("committee_listing")
    object AttendanceScreen: Screen("attendance")
    object CommitteeDetailsScreen: Screen("committee_details")
}