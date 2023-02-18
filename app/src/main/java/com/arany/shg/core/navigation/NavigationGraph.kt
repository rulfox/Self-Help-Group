package com.arany.shg.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.arany.shg.core.util.Constants.NAV_ARG_COMMITTEE_ID
import com.arany.shg.core.navigation.Screen.*
import com.arany.shg.feature_attendance.presentation.AttendanceScreen
import com.arany.shg.feature_committee.presentation.AddCommitteeScreen
import com.arany.shg.feature_committee.presentation.CommitteeDetailsScreen
import com.arany.shg.feature_committee.presentation.CommitteeListingScreen
import com.arany.shg.feature_dashboard.presentation.DashboardScreen
import com.arany.shg.feature_loan.presentation.AddLoanScreen
import com.arany.shg.feature_member.presentation.util.add_member.AddMemberScreen
import com.arany.shg.feature_onboarding.presentation.login.LoginScreen
import com.arany.shg.feature_shg.presentation.create_shg.CreateSelfHelpGroupScreen
import com.arany.shg.feature_thrift.presentation.AddThriftScreen
import com.arany.shg.feature_role.presentation.AddRoleScreen

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = LoginScreen.route,
        modifier = modifier
    ){
        composable(route = LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = CreateSelfHelpGroupScreen.route) {
            CreateSelfHelpGroupScreen(navController = navController)
        }

        composable(route = AddThriftScreen.route.plus("/{$NAV_ARG_COMMITTEE_ID}"), arguments = listOf(navArgument(NAV_ARG_COMMITTEE_ID) {
            type = NavType.IntType
        })){ backStackEntry ->
            val committeeId = backStackEntry.arguments?.getInt(NAV_ARG_COMMITTEE_ID)
            AddThriftScreen(navController = navController)
        }

        composable(route = AddMemberScreen.route){
            AddMemberScreen(navController = navController)
        }

        composable(route = AddCommitteeScreen.route){
            AddCommitteeScreen(navController = navController)
        }
        composable(route = AttendanceScreen.route.plus("/{$NAV_ARG_COMMITTEE_ID}"), arguments = listOf(navArgument(NAV_ARG_COMMITTEE_ID) {
            type = NavType.IntType
        })){ backStackEntry ->
            val committeeId = backStackEntry.arguments?.getInt(NAV_ARG_COMMITTEE_ID)
            AttendanceScreen(navController = navController)
        }
        composable(route = DashboardScreen.route){
            DashboardScreen(navController = navController)
        }

        composable(route = CommitteeListingScreen.route) {
            CommitteeListingScreen(navController = navController)
        }
        composable(route = CommitteeDetailsScreen.route.plus("/{$NAV_ARG_COMMITTEE_ID}"), arguments = listOf(navArgument(NAV_ARG_COMMITTEE_ID) {
            type = NavType.IntType
        })){ backStackEntry ->
            val committeeId = backStackEntry.arguments?.getInt(NAV_ARG_COMMITTEE_ID)
            CommitteeDetailsScreen(navController = navController)
        }
        composable(route = AddLoanScreen.route.plus("/{$NAV_ARG_COMMITTEE_ID}"), arguments = listOf(navArgument(NAV_ARG_COMMITTEE_ID) {
            type = NavType.IntType
        })){ backStackEntry ->
            val committeeId = backStackEntry.arguments?.getInt(NAV_ARG_COMMITTEE_ID)
            AddLoanScreen(navController = navController)
        }
        composable(route = DashboardScreen.route){
            DashboardScreen(navController = navController)
        }
        composable(route = AddRoleScreen.route){
            AddRoleScreen(navController = navController)
        }
    }
}