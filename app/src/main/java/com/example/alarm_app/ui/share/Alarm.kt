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
import androidx.compose.ui.unit.dp
import com.example.alarm_app.R
import com.example.alarm_app.data.Alarm

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
                    Alarm(it, { onAlarmRemove(index) })
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
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            TimeSelector(alarm.hour, alarm.minute)
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
