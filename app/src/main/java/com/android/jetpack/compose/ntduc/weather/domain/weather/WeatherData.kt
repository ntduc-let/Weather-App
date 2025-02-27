package com.android.jetpack.compose.ntduc.weather.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val humidity: Double,
    val pressure: Double,
    val windSpeed: Double,
    val weatherType: WeatherType
)