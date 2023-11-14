@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.alarm_app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.InputChip
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.alarm_app.ui.share.displayText
import com.kizitonwose.calendar.core.daysOfWeek

@Composable
fun AlarmSetting() {
    CycleSelector()
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CycleSelector(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        InputChip(
            onClick = { },
            label = { Text(text = "國定上班日") },
            selected = false,
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            daysOfWeek().map {
                InputChip(
                    onClick = { },
                    label = { Text(text = it.displayText()) },
                    selected = true,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
        ) {
            OutlinedButton(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "重設")
            }
            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = "套用")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AlarmSettingPreview() {
    AlarmSetting()
}