package com.example.alarm_app.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alarm_app.R
import com.example.alarm_app.ui.share.AlarmGroup
import kotlinx.coroutines.launch

@Composable
fun AlarmGroupScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AlarmGroupViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val alarmSet by viewModel.alarmSet.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        IconButton(onClick = {
            coroutineScope.launch {
                try {
                    viewModel.updateAlarmSet()
                    navigateBack()
                }
                catch (ex: Exception) {
                    Log.e(AlarmGroupViewModel.TAG, ex.message.toString())
                }
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_save),
                contentDescription = "儲存"
            )
        }

        TextField(
            label = { Text(text = "名稱") },
            value = alarmSet.name,
            onValueChange = { viewModel.changeName(it) },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
        TextField(
            label = { Text(text = "描述") },
            value = alarmSet.description ?: "",
            onValueChange = { viewModel.changeDescription(it) },
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
        AlarmGroup(
            alarms = alarmSet.alarms,
            onAlarmAdd = { viewModel.addAlarm() },
            onAlarmRemove = { viewModel.removeAlarm(it) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlarmGroupPreview() {
    AlarmGroupScreen({})
}