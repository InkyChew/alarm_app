package com.example.alarm_app.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "alarmSet")
data class AlarmSet(
    @PrimaryKey(autoGenerate = true)
    var id: Int  = 0,
    var name: String = "",
    var description: String? = null,
    @Ignore
    val alarms: List<AlarmSetAlarm>? = null
)

data class AlarmSetWithAlarms(
    @Embedded val alarmSet: AlarmSet,
    @Relation(
        parentColumn = "id",
        entityColumn = "alarmSetId"
    )
    val alarms: List<AlarmSetAlarm>? = null
)