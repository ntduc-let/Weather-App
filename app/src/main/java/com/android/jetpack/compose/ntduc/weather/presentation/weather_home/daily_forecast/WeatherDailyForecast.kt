package com.android.jetpack.compose.ntduc.weather.presentation.weather_home.daily_forecast

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherInfo

fun LazyListScope.WeatherDailyForecast(
    weatherInfo: WeatherInfo?,
    onClickDailyWeather: (Int) -> Unit
) {
    val weatherDataDaily = weatherInfo?.weatherDataDaily
    if (!weatherDataDaily.isNullOrEmpty()) {
        item {
            TitleWeatherDailyForecast()
        }

        items(items = weatherDataDaily, key = {
            it.time
        }) { data ->
            DailyWeatherDisplay(weatherInfo = weatherInfo, weatherData = data, onClickDailyWeather = onClickDailyWeather)
        }
    }
}