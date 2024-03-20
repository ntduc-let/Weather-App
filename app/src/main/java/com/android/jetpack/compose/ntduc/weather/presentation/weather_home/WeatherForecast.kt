package com.android.jetpack.compose.ntduc.weather.presentation.weather_home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.jetpack.compose.ntduc.weather.R

@Composable
fun WeatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
) {
    val info = state.weatherInfo
    info?.weatherDataNext24Hour?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.hourly_forecast),
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                content = {
                    items(count = data.size) { index ->
                        HourlyWeatherDisplay(
                            currentWeatherData = info.currentWeatherData,
                            weatherData = data[index],
                            weatherUnit = info.weatherUnit,
                            modifier = Modifier
                        )
                    }
                },
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            )
        }
    }
}