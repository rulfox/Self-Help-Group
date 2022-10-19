package com.arany.shg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arany.shg.core.util.Screen
import com.arany.shg.feature_member.presentation.util.add_member.AddMemberScreen
import com.arany.shg.feature_onboarding.presentation.login.LoginScreen
import com.arany.shg.feature_shg.domain.repository.SelfHelpGroupRepository
import com.arany.shg.feature_shg.presentation.create_shg.CreateSelfHelpGroupScreen
import com.arany.shg.feature_thrift.presentation.AddThriftScreen
import com.arany.shg.ui.theme.SelfHelpGroupTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var selfHelpGroupRepository: SelfHelpGroupRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelfHelpGroupTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route
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
                    }
                }
            }
        }
    }
}