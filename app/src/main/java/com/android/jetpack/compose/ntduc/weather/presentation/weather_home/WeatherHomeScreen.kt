package com.android.jetpack.compose.ntduc.weather.presentation.weather_home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
                    state = viewModel.state,
                )
                Spacer(modifier = Modifier.height(16.dp))
                WeatherForecast(state = viewModel.state)
            }
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