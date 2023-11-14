package com.example.alarm_app

import android.app.Application
import com.example.alarm_app.data.AppContainer
import com.example.alarm_app.data.AppDataContainer

class AlarmApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}