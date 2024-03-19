package com.android.jetpack.compose.ntduc.weather.presentation.weather_home

import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
