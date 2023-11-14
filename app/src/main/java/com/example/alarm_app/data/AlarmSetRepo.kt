package com.example.alarm_app.data

import kotlinx.coroutines.flow.Flow

interface IAlarmSetRepo {
    fun getStreamWithAlarms(id: Int): Flow<AlarmSetWithAlarms>
    suspend fun addAlarmSetWithAlarms(item: AlarmSet)
    suspend fun updateAlarmSetWithAlarms(item: AlarmSet)
    suspend fun delete(item: AlarmSet)
    suspend fun deleteAlarm(item: AlarmSetAlarm)
    fun getAllStream(): Flow<List<AlarmSet>>
    fun getStream(id: Int): Flow<AlarmSet>
}

class AlarmSetRepo(private val alarmSetDao: AlarmSetDao) : IAlarmSetRepo {
    override fun getStreamWithAlarms(id: Int): Flow<AlarmSetWithAlarms> = alarmSetDao.getWithAlarms(id)
    override suspend fun addAlarmSetWithAlarms(item: AlarmSet) = alarmSetDao.addAlarmSetWithAlarms(item)
    override suspend fun updateAlarmSetWithAlarms(item: AlarmSet) = alarmSetDao.updateAlarmSetWithAlarms(item)
    override suspend fun delete(item: AlarmSet) = alarmSetDao.delete(item)
    override suspend fun deleteAlarm(item: AlarmSetAlarm) = alarmSetDao.deleteAlarm(item)
    override fun getAllStream(): Flow<List<AlarmSet>> = alarmSetDao.getAll()
    override fun getStream(id: Int): Flow<AlarmSet> = alarmSetDao.get(id)
}