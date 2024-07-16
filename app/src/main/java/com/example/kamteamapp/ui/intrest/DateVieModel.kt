package com.example.kamteamapp.ui.intrest

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class DateNDaysViewModel :ViewModel() {

    init {
        getDataState()
    }

    private val _uiState = MutableStateFlow(DateNDaysUiState())
    val uiState: StateFlow<DateNDaysUiState> = _uiState.asStateFlow()

    private val _dataState = MutableStateFlow(DateNDaysDataState())
    val dataState: StateFlow<DateNDaysDataState> = _dataState.asStateFlow()

    private val dateRegex: String = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})\\/(((0[13578]|1[02])\\/(0[1-9]|[12][0-9]|3[01]))|"+
            "((0[469]|11)\\/(0[1-9]|[12][0-9]|30))|(02\\/(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|"+
            "((0[48]|[2468][048]|[3579][26])00))\\/02\\/29)$"

    private fun getDataState() = viewModelScope.launch{
    }

    var birthdaySelected by mutableStateOf("")
        private set

    var enlistmentDaySelected by mutableStateOf("")
        private set

    var dDay by mutableStateOf("")
        private set

    private fun isIllegalBirthdayInputState (current: Boolean) {
        _uiState.update { state ->
            state.copy(isIllegalBirthdayDate = current)
        }
    }

    private fun isIllegalEnlistmentDayInputState (current: Boolean) {
        _uiState.update { state ->
            state.copy(isIllegalEnlistmentDate = current)
        }
    }

    private fun showDetail (current: Boolean) {
        _dataState.update { state ->
            state.copy(show = current)
        }
    }
    fun errorState (current: Boolean) {
        _uiState.update { state ->
            state.copy(error = current)
        }
    }

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
    val today: LocalDate = LocalDate.now()


}