package com.example.alarm_app.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alarm_app.data.Day
import com.example.alarm_app.data.DayAlarm
import com.example.alarm_app.data.IAlarmSetRepo
import com.example.alarm_app.data.IDayRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.YearMonth

class MainViewModel (
    private val dayRepo: IDayRepo,
    private val alarmSetRepo: IAlarmSetRepo
) : ViewModel() {
    private var _dayList: MutableStateFlow<List<Day>> = MutableStateFlow(listOf())
    val dayList: StateFlow<List<Day>> = _dayList.asStateFlow()

    private var _yearMonth: MutableState<YearMonth> = mutableStateOf(YearMonth.now())

    private var _day: MutableStateFlow<Day> = MutableStateFlow(Day())
    val day: StateFlow<Day> = _day.asStateFlow()

    private var _alarmSetOptions: MutableStateFlow<Map<Int, String>> = MutableStateFlow(mapOf())
    val alarmSetOptions: StateFlow<Map<Int, String>> = _alarmSetOptions.asStateFlow()

    init {
        getDayList()
        getAlarmSetOptions()
    }

    private fun getDayList() {
        viewModelScope.launch {
            try {
                dayRepo.getAllStream(_yearMonth.value)
                    .collect{ _dayList.value = it }
            }
            catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    fun setYearMonth(yearMonth: YearMonth) {
        _yearMonth.value = yearMonth
        getDayList()
    }

    fun setTargetDay(day: Day) {
        _day.value = day
    }

    fun addAlarm() {
        _day.update {
            it.copy(alarms = it.alarms?.plus(DayAlarm(it.id)))
        }
    }

    fun removeAlarm(index: Int) {
        _day.update {
            it.copy(alarms = it.alarms?.minus(it.alarms[index]))
        }
    }

    suspend fun updateDayAlarm() {
        dayRepo.update(_day.value)
        getDayList()
    }

    private fun getAlarmSetOptions() {
        viewModelScope.launch {
            try {
                alarmSetRepo.getAllStream()
                    .collect{alarmSetList ->
                        _alarmSetOptions.value = alarmSetList.associate {
                            it.id to it.name
                        }
                    }
            }
            catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    fun getAlarmSetAlarms(id: Int) {
        viewModelScope.launch {
            try {
                alarmSetRepo.getStream(id).collect{
                    _day.apply {
                        val alarms = it.alarms?.map {DayAlarm(value.id) } ?: listOf()
                        update {
                            it.copy(alarms = it.alarms?.plus(alarms))
                        }
                    }
                }
            }
            catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    companion object {
        const val TAG: String = "MainViewModel"
    }
}