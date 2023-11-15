package com.example.alarm_app.ui.share

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alarm_app.R
import com.example.alarm_app.data.Alarm
import com.example.alarm_app.data.AlarmSetAlarm

@Composable
fun AlarmGroup(
    alarms: List<Alarm>? = null,
    onAlarmAdd: () -> Unit,
    onAlarmRemove: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        IconButton(onClick = onAlarmAdd) {
            Icon(Icons.Filled.Add, contentDescription = "add")
        }

        if(alarms != null) {
            LazyColumn {
                itemsIndexed(alarms) {index, it ->
                    Alarm(
                        alarm = it,
                        onAlarmRemove = { onAlarmRemove(index) },
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun Alarm(
    alarm: Alarm,
    onAlarmRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TimeSelector(alarm.hour, alarm.minute,
                onTimeChange = {hr, mn ->
                    alarm.hour = hr
                    alarm.minute = mn
                },
                modifier = Modifier.padding(start = 10.dp).weight(1f)
            )
            Column {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_music),
                        contentDescription = "music setting"
                    )
                }
                IconButton(onClick = {onAlarmRemove()}) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete),
                        contentDescription = "delete"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlarmPreview() {
    AlarmGroup(listOf(AlarmSetAlarm(1), AlarmSetAlarm(2)),{},{})
}