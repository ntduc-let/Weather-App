package com.android.jetpack.compose.ntduc.weather.domain.repository

import com.android.jetpack.compose.ntduc.weather.domain.util.Resource
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}