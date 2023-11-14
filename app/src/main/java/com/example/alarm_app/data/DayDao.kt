package com.example.alarm_app.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import java.time.YearMonth

@Dao
interface DayDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(day: Day)

    @Update
    suspend fun update(day: Day)

    @Delete
    suspend fun delete(day: Day)

    @Query("SELECT * from day WHERE id = :id")
    fun get(id: Int): Flow<Day>

    @Query("SELECT * from day WHERE strftime('%Y-%m', date) = :yearMonth ORDER BY date ASC")
    fun getAll(yearMonth: String): Flow<List<Day>>

    @Transaction
    @Query("SELECT * from day WHERE id = :id")
    fun getWithAlarms(id: Int): Flow<DayWithAlarms>
}