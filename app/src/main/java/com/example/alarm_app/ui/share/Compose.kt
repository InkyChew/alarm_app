package com.example.alarm_app.ui.share

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onFocusedBoundsChanged
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dropdown(options: Map<*, String>, label: String = "", onSelectionChange: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options.entries.firstOrNull()) }
    // We want to react on tap/press on TextField to show menu
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            readOnly = true,
            value = selectedOption?.value ?: "",
            onValueChange = { onSelectionChange(selectedOption!!.key.toString()) },
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.value) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Composable
fun TimeSelector(hour: Int, minute: Int,
                 onTimeChange: (Int, Int) -> Unit,
                 modifier: Modifier = Modifier) {
    var hr by remember { mutableIntStateOf(hour) }
    var mn by remember { mutableIntStateOf(minute) }
    var hrStr by remember { mutableStateOf(String.format("%02d", hour)) }
    var mnStr by remember { mutableStateOf(String.format("%02d", minute)) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        TextField(
            value = hrStr,
            onValueChange = {
                val h = it.toIntOrNull()
                if(h != null) {
                    hr = h.coerceIn(0..23)
                    hrStr = hr.toString()
                    onTimeChange(hr, mn)
                } else hrStr = ""
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.weight(1f)
                .onFocusChanged { hrStr = String.format("%02d", hr) }
        )
        Text(text = ":")
        TextField(
            value = mnStr,
            onValueChange = {
                val m = it.toIntOrNull()
                if(m != null) {
                    mn = m.coerceIn(0..59)
                    mnStr = mn.toString()
                    onTimeChange(hr, mn)
                } else mnStr = ""
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.weight(1f)
                .onFocusChanged { mnStr = String.format("%02d", mn) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ComponentPreview() {

}