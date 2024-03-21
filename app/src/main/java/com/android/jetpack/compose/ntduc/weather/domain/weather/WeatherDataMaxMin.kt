package com.android.jetpack.compose.ntduc.weather.domain.weather

import java.time.LocalDateTime

data class WeatherDataMaxMin(
    val time: LocalDateTime,
    val temperatureCelsiusMax: Double,
    val temperatureCelsiusMin: Double,
    val humidityMax: Double,
    val humidityMin: Double,
    val pressureMax: Double,
    val pressureMin: Double,
    val windSpeedMax: Double,
    val windSpeedMin: Double,
)