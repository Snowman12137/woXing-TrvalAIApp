package com.example.kamteamapp.componets.DatePicker


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class DateInputState (input: String, pickerInput: String) {

    var dayInput by mutableStateOf(input)
        private set

    var datePickerInput by mutableStateOf(pickerInput)

    fun updateText (day: String): String {
        dayInput = day
        return dayInput
    }

    companion object {
        val Saver: Saver<DateInputState, *> = listSaver(
            save = { listOf(it.dayInput, it.datePickerInput) },
            restore = {
                DateInputState(
                    input = it[0],
                    pickerInput = it[1]
                )
            }
        )
    }
}

@Composable
fun rememberDateInputState (input: String): DateInputState =
    rememberSaveable (input, saver = DateInputState.Saver){
        DateInputState(input, input)
    }

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@Composable
fun CustomDatePicker (
    label: String,
    onInputDateChanged: (String) -> Unit,
    dateInputState: DateInputState,
    isIllegalInput: Boolean
) {
    val isOpen = rememberSaveable { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    ) // get selected date
    val form = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    DateTextField(
        text = dateInputState.dayInput,
        label = label,
        onChange = { onInputDateChanged(dateInputState.updateText(it)) } ,
        trailingIcon = {
            IconButton(onClick = { isOpen.value = true}) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Date Picker")
                if (isOpen.value) {
                    CustomDatePickerDialog(
                        state = datePickerState,
                        onConfirmButtonClicked = {
                            isOpen.value = false
                            if (it != null) {
                                dateInputState.datePickerInput = Instant
                                    .ofEpochMilli(it)
                                    .atZone(ZoneId.of("UTC+8"))
                                    .toLocalDate().format(form)
                                onInputDateChanged(dateInputState
                                    .updateText(dateInputState.datePickerInput))
                            }
                        },
                        onDismissRequest = { isOpen.value = false },
                    )
                }
            }
        },
        isIllegalInput = isIllegalInput
    )
}

@ExperimentalMaterial3Api
@Composable
fun CustomDatePickerDialog (
    state: DatePickerState,
    confirmButtonText: String = "OK",
    dismissButtonText: String = "Cancel",
    onDismissRequest: () -> Unit,
    onConfirmButtonClicked: (Long?) -> Unit
) {
    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = { onConfirmButtonClicked(state.selectedDateMillis) }) {
                Text(text = confirmButtonText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = dismissButtonText)
            }
        },
        content = {
            DatePicker(
                state = state,
                showModeToggle = false,
                headline = null,
                title = null,
            )
        }
    )
}

@Composable
fun DateTextField (
    modifier: Modifier = Modifier,
    text: String,
    trailingIcon: @Composable (() -> Unit)? = null,
    onChange: (String) -> Unit,
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Number,
    keyBoardActions: KeyboardActions = KeyboardActions(),
    isEnabled: Boolean = true,
    label: String,
    supportingText: String = "yyyy/mm/dd",
    isIllegalInput: Boolean
) {
    OutlinedTextField(
        value = text,
        onValueChange = onChange,
        modifier = modifier.fillMaxWidth(),
        textStyle = TextStyle(fontSize = 18.sp),
        keyboardActions = keyBoardActions,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        enabled = isEnabled,
        trailingIcon = trailingIcon,
        label = {
            Text(text = label, style = TextStyle(fontSize = 18.sp))
        },
        singleLine = true,
        supportingText = {
            Text(text = supportingText, style = TextStyle(fontSize = 18.sp))
        },
        isError = isIllegalInput
    )
}