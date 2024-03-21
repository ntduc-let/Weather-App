package com.android.jetpack.compose.ntduc.weather.presentation.weather_home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.android.jetpack.compose.ntduc.weather.presentation.weather_home.daily_forecast.WeatherDailyForecast
import com.android.jetpack.compose.ntduc.weather.presentation.weather_home.hourly_forecast.WeatherHourlyForecast
import com.android.jetpack.compose.ntduc.weather.presentation.weather_home.now.WeatherCard

@Composable
fun WeatherHomeScreen(viewModel: WeatherViewModel, modifier: Modifier, onRequestLocationPermission: () -> Unit, onEnableGps: () -> Unit) {
    LaunchedEffect(key1 = Unit) {
        viewModel.loadWeatherInfo()
    }

    Box(
        modifier = modifier
    ) {
        LazyColumn {
            item {
                WeatherCard(
                    weatherInfo = viewModel.state.weatherInfo,
                )
            }
            item {
                WeatherHourlyForecast(weatherInfo = viewModel.state.weatherInfo)
            }

            WeatherDailyForecast(weatherInfo = viewModel.state.weatherInfo)
        }
        if (viewModel.state.isLoading) {
            WeatherLoading(modifier = Modifier.align(Alignment.Center))
        }
        viewModel.state.error?.let { error ->
            WeatherError(
                error = error,
                modifier = Modifier.align(Alignment.Center),
                onRequestLocationPermission = onRequestLocationPermission,
                onEnableGps = onEnableGps,
                onReload = {
                    viewModel.loadWeatherInfo()
                }
            )
        }
    }
}