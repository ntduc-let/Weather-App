package com.android.jetpack.compose.ntduc.weather.presentation.weather_home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.android.jetpack.compose.ntduc.weather.presentation.weather_home.daily_forecast.WeatherDailyForecast
import com.android.jetpack.compose.ntduc.weather.presentation.weather_home.hourly_forecast.WeatherHourlyForecast
import com.android.jetpack.compose.ntduc.weather.presentation.weather_home.now.WeatherCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherHomeScreen(
    viewModel: WeatherViewModel,
    modifier: Modifier,
    onRequestLocationPermission: () -> Unit,
    onEnableGps: () -> Unit,
    onClickDailyWeather: (Int) -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.loadWeatherInfo()
    }
    val pullRefreshState = rememberPullRefreshState(viewModel.state.isLoading, { viewModel.loadWeatherInfo() })

    Box(
        modifier = modifier.pullRefresh(pullRefreshState)
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

            WeatherDailyForecast(weatherInfo = viewModel.state.weatherInfo, onClickDailyWeather = onClickDailyWeather)
        }
        if (viewModel.state.isLoading && viewModel.state.weatherInfo == null) {
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

        if (viewModel.state.weatherInfo != null){
            PullRefreshIndicator(viewModel.state.isLoading, pullRefreshState, Modifier.align(Alignment.TopCenter))
        }
    }
}