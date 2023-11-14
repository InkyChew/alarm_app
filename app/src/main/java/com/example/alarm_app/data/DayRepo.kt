package com.example.alarm_app.data

import kotlinx.coroutines.flow.Flow
import java.time.YearMonth

interface IDayRepo {
    fun getAllStream(yearMonth: YearMonth): Flow<List<Day>>
    fun getStream(id: Int): Flow<Day>
    suspend fun insert(item: Day)
    suspend fun delete(item: Day)
    suspend fun update(item: Day)
}

class DayRepo(private val dayDao: DayDao) : IDayRepo {
    override fun getAllStream(yearMonth: YearMonth): Flow<List<Day>> = dayDao.getAll(yearMonth.toString())
    override fun getStream(id: Int): Flow<Day> = dayDao.get(id)
    override suspend fun insert(item: Day) = dayDao.insert(item)
    override suspend fun delete(item: Day) = dayDao.delete(item)
    override suspend fun update(item: Day) = dayDao.update(item)
}