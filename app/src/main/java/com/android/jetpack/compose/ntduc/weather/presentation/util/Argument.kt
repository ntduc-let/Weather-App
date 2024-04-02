package com.android.jetpack.compose.ntduc.weather.presentation.util

sealed class Argument(val key: String) {
    data object DayOfYear : Argument("day_of_year")
}