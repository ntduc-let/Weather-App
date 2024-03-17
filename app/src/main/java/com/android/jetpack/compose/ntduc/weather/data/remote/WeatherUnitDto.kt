package com.android.jetpack.compose.ntduc.weather.data.remote

import com.squareup.moshi.Json

data class WeatherUnitDto(
    @field:Json(name = "time")
    val time: String,
    @field:Json(name = "temperature_2m")
    val temperature: String,
    @field:Json(name = "relative_humidity_2m")
    val humidity: String,
    @field:Json(name = "pressure_msl")
    val pressure: String,
    @field:Json(name = "windspeed_10m")
    val windSpeed: String,
    @field:Json(name = "weathercode")
    val weatherCode: String,
)
