package com.android.jetpack.compose.ntduc.weather.presentation.weather_daily

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun WeatherTitleDaily(
    modifier: Modifier = Modifier,
    viewModel: WeatherDailyViewModel
) {
    val state = viewModel.state
    state.weatherDataPerDay[state.dayOfYearSelected]?.ifEmpty { null }?.first()?.time?.let {
        Text(text = it.format(DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy", Locale.ENGLISH)))
    }
}