package com.example.alarm_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

open class Alarm(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var hour: Int = 12,
    var minute: Int = 0,
    var musicId: Int = 1
)

@Entity(tableName = "alarmSetAlarm")
data class AlarmSetAlarm(
    var alarmSetId: Int
): Alarm()

@Entity(tableName = "dayAlarm")
data class DayAlarm(
    val dayId: Int
): Alarm()