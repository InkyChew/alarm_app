package com.example.alarm_app.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alarm_app.data.AlarmSet
import com.example.alarm_app.data.AlarmSetAlarm
import com.example.alarm_app.data.IAlarmSetRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlarmGroupViewModel (
    savedStateHandle: SavedStateHandle,
    private val alarmSetRepo: IAlarmSetRepo
) : ViewModel() {

    private val id: Int? = savedStateHandle["alarmSetId"]

    private var _alarmSet: MutableStateFlow<AlarmSet> = MutableStateFlow(AlarmSet())
    val alarmSet: StateFlow<AlarmSet> = _alarmSet.asStateFlow()

    init {
        if(id != null) getAlarmSet(id)
    }

    fun addAlarm() {
        _alarmSet.update {
            val alarms = it.alarms ?: listOf()
            it.copy(alarms = alarms.plus(AlarmSetAlarm(_alarmSet.value.id)))
        }
    }

    fun removeAlarm(index: Int) {
        viewModelScope.launch {
            try {
                _alarmSet.apply {
                    value.alarms?.let { alarms ->
                        val alarm = alarms[index]
                        alarmSetRepo.deleteAlarm(alarm)
                        update {
                            it.copy(alarms = it.alarms?.minus(alarm))
                        }
                    }
                }
            }
            catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    private fun getAlarmSet(id: Int) {
        viewModelScope.launch {
            try {
                alarmSetRepo.getStreamWithAlarms(id).collect{alarmSetWithAlarms ->
                    _alarmSet.value = alarmSetWithAlarms.alarmSet
                    _alarmSet.update {
                        it.copy(alarms = alarmSetWithAlarms.alarms)
                    }
                }
            }
            catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    fun changeName(name: String) {
        _alarmSet.update { it.copy(name = name) }
    }

    fun changeDescription(desc: String) {
        _alarmSet.update { it.copy(description = desc) }
    }

    suspend fun updateAlarmSet() {
        _alarmSet.value.apply {
            if(id == 0) {
                alarmSetRepo.addAlarmSetWithAlarms(this)
            } else {
                alarmSetRepo.updateAlarmSetWithAlarms(this)
            }
        }
    }

    companion object {
        const val TAG: String = "AlarmGroupViewModel"
    }
}