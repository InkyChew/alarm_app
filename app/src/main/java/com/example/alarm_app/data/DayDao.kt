package com.example.alarm_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DayDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(day: Day)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlarm(alarm: DayAlarm)

    @Update
    suspend fun update(day: Day)

    @Update
    suspend fun updateAlarm(alarm: DayAlarm)

    @Transaction
    suspend fun updateAlarms(alarms: List<DayAlarm>) {
        alarms.forEach {
            if(it.id == 0) {
                insertAlarm(it)
            } else {
                updateAlarm(it)
            }
        }
    }

    @Delete
    suspend fun delete(day: Day)

    @Delete
    suspend fun deleteAlarm(alarm: DayAlarm)

    @Query("SELECT * from day WHERE id = :id")
    fun get(id: Int): Flow<Day>

    @Query("SELECT * from day WHERE strftime('%Y-%m', date) = :yearMonth ORDER BY date ASC")
    fun getAll(yearMonth: String): Flow<List<Day>>

    @Query("SELECT * from day WHERE strftime('%Y-%m', date) = :yearMonth ORDER BY date ASC")
    fun getAllWithAlarms(yearMonth: String): Flow<List<DayWithAlarms>>

    @Transaction
    @Query("SELECT * from day WHERE id = :id")
    fun getWithAlarms(id: Int): Flow<DayWithAlarms>
}