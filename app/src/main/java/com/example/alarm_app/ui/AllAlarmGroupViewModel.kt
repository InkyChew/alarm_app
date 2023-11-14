package com.example.alarm_app.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alarm_app.data.AlarmSet
import com.example.alarm_app.data.IAlarmSetRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AllAlarmGroupViewModel (
    private val alarmSetRepo: IAlarmSetRepo
) : ViewModel() {

    private var _alarmSetList: MutableStateFlow<List<AlarmSet>> = MutableStateFlow(listOf())
    val alarmSetList: StateFlow<List<AlarmSet>> = _alarmSetList.asStateFlow()

    fun getAlarmSetList() {
        viewModelScope.launch {
            try {
                alarmSetRepo.getAllStream().collect{
                    _alarmSetList.value = it
                }
            }
            catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    fun deleteAlarmSet(alarmSet: AlarmSet) {
        viewModelScope.launch {
            try {
                alarmSetRepo.delete(alarmSet)
                _alarmSetList.update {
                    it.toMutableList().apply { remove(alarmSet) }
                }
            }
            catch (ex: Exception) {
                Log.e(TAG, ex.message.toString())
            }
        }
    }

    companion object {
        const val TAG: String = "AllAlarmGroupScreenViewModel"
    }
}