package com.arany.shg.core.composables

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arany.shg.core.navigation.NavigationHelper.currentRoute
import com.arany.shg.core.navigation.Screen

@Composable
fun BottomNavigationUI(navController: NavController) {
    BottomNavigation {
        val items = listOf(
            Screen.AddMemberScreen,
            Screen.AddRoleScreen,
            Screen.AddCommitteeScreen,
            Screen.CommitteeListingScreen,
        )
        items.forEach { item ->
            BottomNavigationItem(
                label = { Text(text = item.title) },
                selected = currentRoute(navController) == item.route,
                icon = item.navIcon,
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                })
        }
    }
}