package com.example.kamteamapp.ui.intrest

data class DateNDaysUiState(
    val isIllegalBirthdayDate: Boolean = false,
    val isIllegalEnlistmentDate: Boolean = false,
    val error: Boolean = false,
    val backHomeCountDown: Long = 0
)

data class DateNDaysDataState(
    val id: Int = 0,
    val birthdaySelect: String = "",
    val enlistmentDaySelect: String = "",
    val dDay: String = "0",
    val day2Leave: String = "",
    val show: Boolean = false,
)