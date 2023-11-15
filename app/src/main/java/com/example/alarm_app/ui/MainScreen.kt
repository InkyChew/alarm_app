package com.example.alarm_app.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alarm_app.R
import com.example.alarm_app.ui.share.AlarmGroup
import com.example.alarm_app.ui.share.Dropdown
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val dayList by viewModel.dayList.collectAsState()
    val day by viewModel.day.collectAsState()
    val alarmSetOptions by viewModel.alarmSetOptions.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Calendar(
            dayList = dayList,
            onYearMonthChange = { viewModel.setYearMonth(it) },
            onDayClick = { viewModel.setTargetDay(it) }
        )


        IconButton(onClick = {
            coroutineScope.launch {
                try {
                    viewModel.updateAlarms()
                }
                catch (ex: Exception) {
                    Log.e(MainViewModel.TAG, ex.message.toString())
                }
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_save),
                contentDescription = "儲存"
            )
        }
        Dropdown(
            options = alarmSetOptions,
            label = stringResource(id = R.string.alarm_group),
            onSelectionChange = { viewModel.getAlarmSetAlarms(it.toInt()) }
        )
        AlarmGroup(
            alarms = day.alarms,
            onAlarmAdd = { viewModel.addAlarm() },
            onAlarmRemove = { viewModel.removeAlarm(it) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}