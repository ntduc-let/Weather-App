package com.android.jetpack.compose.ntduc.weather.data.remote

import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = "hourly_units")
    val weatherUnit: WeatherUnitDto,
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)
