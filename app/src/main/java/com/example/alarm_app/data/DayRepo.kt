package com.example.alarm_app.data

import kotlinx.coroutines.flow.Flow
import java.time.YearMonth

interface IDayRepo {
    fun getAllStreamWithAlarms(yearMonth: YearMonth): Flow<List<DayWithAlarms>>
    fun getAllStream(yearMonth: YearMonth): Flow<List<Day>>
    fun getStreamWithAlarms(id: Int): Flow<DayWithAlarms>
    fun getStream(id: Int): Flow<Day>
    suspend fun insert(item: Day)
    suspend fun delete(item: Day)
    suspend fun update(item: Day)
    suspend fun updateAlarms(items: List<DayAlarm>)
    suspend fun deleteAlarm(item: DayAlarm)
}

class DayRepo(private val dayDao: DayDao) : IDayRepo {
    override fun getAllStreamWithAlarms(yearMonth: YearMonth): Flow<List<DayWithAlarms>> = dayDao.getAllWithAlarms(yearMonth.toString())
    override fun getAllStream(yearMonth: YearMonth): Flow<List<Day>> = dayDao.getAll(yearMonth.toString())
    override fun getStreamWithAlarms(id: Int): Flow<DayWithAlarms> = dayDao.getWithAlarms(id)
    override fun getStream(id: Int): Flow<Day> = dayDao.get(id)
    override suspend fun insert(item: Day) = dayDao.insert(item)
    override suspend fun delete(item: Day) = dayDao.delete(item)
    override suspend fun update(item: Day) = dayDao.update(item)
    override suspend fun updateAlarms(items: List<DayAlarm>) = dayDao.updateAlarms(items)
    override suspend fun deleteAlarm(item: DayAlarm) = dayDao.deleteAlarm(item)
}