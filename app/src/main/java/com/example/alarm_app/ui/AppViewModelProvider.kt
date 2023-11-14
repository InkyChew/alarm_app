package com.example.alarm_app.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.alarm_app.AlarmApplication

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for MainViewModel
        initializer {
            MainViewModel(
                alarmApplication().container.dayRepo,
                alarmApplication().container.alarmSetRepo
            )
        }

        // Initializer for AllAlarmGroupViewModel
        initializer {
            AllAlarmGroupViewModel(alarmApplication().container.alarmSetRepo)
        }

        // Initializer for AlarmGroupViewModel
        initializer {
            AlarmGroupViewModel(
                this.createSavedStateHandle(),
                alarmApplication().container.alarmSetRepo
            )
        }
    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [alarmApplication].
 */
fun CreationExtras.alarmApplication(): AlarmApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AlarmApplication)