package com.example.alarm_app.ui.navigation

import androidx.annotation.StringRes
import com.example.alarm_app.R

interface NavigationDestination { /**
     * Unique name to define the path for a composable
     */
    val route: String

    /**
     * String resource id to that contains title to be displayed for the screen.
     */
    val titleRes: Int
}

val navigationDestinationMap: Map<String, NavigationDestination> = mapOf(
    "MainDestination" to MainDestination,
    "AllAlarmGroupDestination" to AllAlarmGroupDestination,
    "AlarmGroupDestination" to AlarmGroupDestination,
)

object MainDestination : NavigationDestination {
    override val route = "main"
    @StringRes override val titleRes = R.string.app_name
}

object AllAlarmGroupDestination : NavigationDestination {
    override val route = "all_alarm_group"
    @StringRes override val titleRes = R.string.all_alarm_group
}

object AlarmGroupDestination : NavigationDestination {
    override val route = "alarm_group"
    @StringRes override val titleRes = R.string.alarm_group
    const val argName = "alarmSetId"
    val routeWithArgs = "$route/{$argName}"
}