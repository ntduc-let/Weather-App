package com.android.jetpack.compose.ntduc.weather.presentation.weather_daily

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WeatherDailyScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherDailyViewModel
) {
    LazyColumn(modifier = modifier) {
        item {
            WeatherTitleDaily(viewModel = viewModel)
        }
    }
}