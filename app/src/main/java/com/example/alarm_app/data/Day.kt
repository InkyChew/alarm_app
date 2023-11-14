package com.example.alarm_app.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.time.LocalDate

@Entity(tableName = "day")
data class Day (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var date: String = LocalDate.now().toString(),
    var name: String = "",
    var description: String = "",
    @Ignore
    val alarms: List<DayAlarm>? = null
)

data class DayWithAlarms(
    @Embedded val day: Day,
    @Relation(
        parentColumn = "id",
        entityColumn = "dayId"
    )
    val alarms: List<DayAlarm>? = null
)