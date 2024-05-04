package com.arany.shg

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.arany.shg.core.composables.BottomNavigationUI
import com.arany.shg.core.navigation.DrawerBody
import com.arany.shg.core.navigation.DrawerHeader
import com.arany.shg.core.navigation.Navigation
import com.arany.shg.core.navigation.NavigationHelper.AppBarWithArrow
import com.arany.shg.core.navigation.NavigationHelper.HomeAppBar
import com.arany.shg.core.navigation.NavigationHelper.currentRoute
import com.arany.shg.core.navigation.NavigationHelper.currentScreen
import com.arany.shg.core.navigation.NavigationHelper.getNavigationItems
import com.arany.shg.core.navigation.NavigationHelper.navigationTitle
import com.arany.shg.core.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val isAppBarVisible = remember { mutableStateOf(true) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            when (val screen = currentScreen(navController)) {
                Screen.DashboardScreen -> {
                    if (isAppBarVisible.value) {
                        HomeAppBar(title = screen.title, openDrawer = {
                            scope.launch {
                                scaffoldState.drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }, openFilters = {
                            isAppBarVisible.value = false
                        })
                    }
                }
                else -> {
                    AppBarWithArrow(navigationTitle(navController)) {
                        navController.popBackStack()
                    }
                }
            }
        },
        drawerContent = {
            DrawerHeader()
            DrawerBody(drawerItems = getNavigationItems(), onItemClick = { screen ->
                scope.launch {
                    navController.navigate(screen.route) {
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
                    scaffoldState.drawerState.close()
                }
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.CreateSelfHelpGroupScreen.route) }) {
                Icon(Icons.Rounded.Add, contentDescription = "Add")
            }
        },
        bottomBar = {
            when (currentRoute(navController)) {
                Screen.DashboardScreen.route -> {
                    BottomNavigationUI(navController)
                }
            }
        }
    ) { padding ->
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Navigation(navController = navController, modifier = Modifier)
        }
    }
}