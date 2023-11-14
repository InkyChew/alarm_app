package com.example.alarm_app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.alarm_app.R
import com.example.alarm_app.data.AlarmSet

@Composable
fun AllAlarmGroupScreen(
    navigateToAlarmGroup: (Int?) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AllAlarmGroupViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    viewModel.getAlarmSetList()
    val alarmSetList by viewModel.alarmSetList.collectAsState()

    Column(
        modifier = modifier
    ) {
        IconButton(onClick = { navigateToAlarmGroup(null) }) {
            Icon(Icons.Filled.Add, contentDescription = "add")
        }

        LazyColumn {
            items(alarmSetList) {
                AlarmGroupItem(
                    it,
                    { navigateToAlarmGroup(it.id) },
                    { viewModel.deleteAlarmSet(it) }
                )
            }
        }
    }
}

@Composable
fun AlarmGroupItem(
    alarmSet: AlarmSet,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = alarmSet.name,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = alarmSet.description ?: "",
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                ) {
                    IconButton(onClick = onEdit) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "edit"
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Filled.Settings,
                            contentDescription = "music setting"
                        )
                    }
                    IconButton(onClick = onDelete) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = "delete"
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AllAlarmGroupPreview() {
    AllAlarmGroupScreen({})
}