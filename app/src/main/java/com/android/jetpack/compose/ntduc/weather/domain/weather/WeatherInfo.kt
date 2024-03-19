package com.android.jetpack.compose.ntduc.weather.domain.weather

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?,
    val weatherUnit: WeatherUnit
)
