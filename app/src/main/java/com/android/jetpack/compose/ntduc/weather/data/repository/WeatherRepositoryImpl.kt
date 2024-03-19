package com.android.jetpack.compose.ntduc.weather.data.repository

import android.app.Application
import com.android.jetpack.compose.ntduc.weather.R
import com.android.jetpack.compose.ntduc.weather.data.mappers.toWeatherInfo
import com.android.jetpack.compose.ntduc.weather.data.remote.WeatherApi
import com.android.jetpack.compose.ntduc.weather.domain.repository.WeatherRepository
import com.android.jetpack.compose.ntduc.weather.domain.util.Resource
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val application: Application,
    private val api: WeatherApi,
) : WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            val weatherInfo = api.getWeatherData(
                lat = lat,
                long = long
            ).toWeatherInfo()
            if (weatherInfo.currentWeatherData == null) {
                Resource.Error(application.getString(R.string.no_matching_data_found))
            } else {
                Resource.Success(data = weatherInfo)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: application.getString(R.string.an_error_occurred_please_try_again))
        }
    }
}