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
interface AlarmSetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(alarmSet: AlarmSet): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAlarm(alarm: AlarmSetAlarm)

    @Transaction
    suspend fun addAlarmSetWithAlarms(alarmSet: AlarmSet) {
        val id = insert(alarmSet)
        alarmSet.alarms?.let {alarms ->
            alarms.forEach {
                it.alarmSetId = id.toInt()
                insertAlarm(it)
            }
        }
    }

    @Update
    suspend fun update(alarmSet: AlarmSet)

    @Update
    suspend fun updateAlarm(alarm: AlarmSetAlarm)

    @Delete
    suspend fun delete(alarmSet: AlarmSet)

    @Delete
    suspend fun deleteAlarm(alarm: AlarmSetAlarm)

    @Transaction
    suspend fun updateAlarmSetWithAlarms(alarmSet: AlarmSet) {
        update(alarmSet)
        alarmSet.alarms?.let {alarms ->
            alarms.forEach {
                if(it.id == 0) {
                    insertAlarm(it)
                } else {
                    updateAlarm(it)
                }
            }
        }
    }

    @Query("SELECT * from alarmSet WHERE id = :id")
    fun get(id: Int): Flow<AlarmSet>

    @Query("SELECT * from alarmSet ORDER BY name ASC")
    fun getAll(): Flow<List<AlarmSet>>

    @Transaction
    @Query("SELECT * FROM alarmSet WHERE id = :id")
    fun getWithAlarms(id: Int): Flow<AlarmSetWithAlarms>
}