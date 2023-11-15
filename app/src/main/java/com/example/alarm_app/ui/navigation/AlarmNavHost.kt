package com.example.alarm_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.alarm_app.ui.AlarmGroupScreen
import com.example.alarm_app.ui.AllAlarmGroupScreen
import com.example.alarm_app.ui.MainScreen

@Composable
fun AlarmNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = MainDestination.route,
        modifier = modifier
    ) {
        composable(route = MainDestination.route) {
            MainScreen(
                modifier = Modifier
            )
        }

        composable(route = AllAlarmGroupDestination.route) {
            AllAlarmGroupScreen(
                navigateToAlarmGroup = {
                    var route = AlarmGroupDestination.route
                    if(it != null) route = "${route}/$it"
                    navController.navigate(route)
                },
                modifier = Modifier
            )
        }

        composable(route = AlarmGroupDestination.route){
            AlarmGroupScreen(
                navigateBack = { navController.popBackStack() },
                modifier = Modifier
            )
        }
        composable(route = AlarmGroupDestination.routeWithArgs,
            arguments = listOf(navArgument(AlarmGroupDestination.argName) {
                type = NavType.IntType
            })
        ){
            AlarmGroupScreen(
                navigateBack = { navController.popBackStack() },
                modifier = Modifier
            )
        }
    }
}