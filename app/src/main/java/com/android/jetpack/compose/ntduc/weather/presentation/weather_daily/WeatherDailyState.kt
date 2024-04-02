package com.android.jetpack.compose.ntduc.weather.presentation.weather_home

import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherData

data class WeatherDailyState(
    val weatherDataPerDay: Map<Int, List<WeatherData>> = linkedMapOf(),
    val dayOfYearSelected: Int = 0
)