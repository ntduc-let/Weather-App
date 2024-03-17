package com.android.jetpack.compose.ntduc.baseproject.domain.repository

import com.android.jetpack.compose.ntduc.baseproject.domain.util.Resource
import com.android.jetpack.compose.ntduc.baseproject.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}