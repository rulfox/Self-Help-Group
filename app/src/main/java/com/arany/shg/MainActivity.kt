package com.arany.shg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arany.shg.core.util.Constants
import com.arany.shg.core.util.Constants.NAV_ARG_COMMITTEE_ID
import com.arany.shg.core.util.Screen
import com.arany.shg.feature_attendance.presentation.AttendanceScreen
import com.arany.shg.feature_member.presentation.util.add_member.AddMemberScreen
import com.arany.shg.feature_onboarding.presentation.login.LoginScreen
import com.arany.shg.feature_shg.data.model.SelfHelpGroup
import com.arany.shg.feature_shg.domain.repository.SelfHelpGroupRepository
import com.arany.shg.feature_shg.presentation.create_shg.CreateSelfHelpGroupScreen
import com.arany.shg.feature_committee.presentation.AddCommitteeScreen
import com.arany.shg.feature_thrift.presentation.AddThriftScreen
import com.arany.shg.ui.theme.SelfHelpGroupTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfHelpGroupTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.AddCommitteeScreen.route
                    ){
                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen(navController = navController)
                        }
                        composable(route = Screen.CreateSelfHelpGroupScreen.route) {
                            CreateSelfHelpGroupScreen(navController = navController)
                        }
                        composable(route = Screen.AddThriftScreen.route){
                            AddThriftScreen(navController = navController)
                        }
                        composable(route = Screen.AddMemberScreen.route){
                            AddMemberScreen(navController = navController)
                        }
                        composable(route = Screen.AddCommitteeScreen.route){
                            AddCommitteeScreen(navController = navController)
                        }
                        composable(route = Screen.AttendanceScreen.route.plus("/{$NAV_ARG_COMMITTEE_ID}"), arguments = listOf(navArgument(NAV_ARG_COMMITTEE_ID) {
                            type = NavType.IntType
                        })){ backStackEntry ->
                            val committeeId = backStackEntry.arguments?.getInt(NAV_ARG_COMMITTEE_ID)
                            AttendanceScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}