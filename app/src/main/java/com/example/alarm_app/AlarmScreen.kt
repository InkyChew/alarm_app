package com.example.alarm_app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.alarm_app.ui.navigation.AllAlarmGroupDestination
import com.example.alarm_app.ui.navigation.AlarmNavHost
import com.example.alarm_app.ui.navigation.MainDestination
import com.example.alarm_app.ui.navigation.NavigationDestination
import com.example.alarm_app.ui.navigation.navigationDestinationMap


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyChillDayAppBar(
    currentScreen: NavigationDestination,
    canNavigateBack: Boolean,
    navigateUp:() -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(id = currentScreen.titleRes)) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
    )
}

@Composable
fun AlarmApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = navigationDestinationMap[backStackEntry?.destination?.route] ?: MainDestination

    Scaffold(
        topBar = {
            MyChillDayAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) {
        AlarmNavHost(
            navController = navController,
            modifier = Modifier.padding(it)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlarmScreenPreview() {
    AlarmApp()
}