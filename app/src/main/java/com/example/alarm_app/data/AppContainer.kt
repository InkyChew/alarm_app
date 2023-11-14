package com.example.alarm_app.data

import android.content.Context

interface AppContainer {
    val dayRepo: DayRepo
    val alarmSetRepo: AlarmSetRepo
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val dayRepo: DayRepo by lazy {
        DayRepo(AlarmDatabase.getDatabase(context).dayDao())
    }
    override val alarmSetRepo: AlarmSetRepo by lazy {
        AlarmSetRepo(AlarmDatabase.getDatabase(context).alarmSetDao())
    }
}