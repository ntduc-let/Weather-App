package com.android.jetpack.compose.ntduc.weather.data.remote

import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherData
import com.squareup.moshi.Json

data class WeatherDataDto(
    @field:Json(name = "time")
    val time: List<String>,
    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,
    @field:Json(name = "relative_humidity_2m")
    val humidities: List<Double>,
    @field:Json(name = "pressure_msl")
    val pressures: List<Double>,
    @field:Json(name = "windspeed_10m")
    val windSpeeds: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
)