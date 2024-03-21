package com.android.jetpack.compose.ntduc.weather.presentation.weather_home.hourly_forecast

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.jetpack.compose.ntduc.weather.R
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherData
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherUnit
import java.time.format.DateTimeFormatter

@Composable
fun HourlyWeatherDisplay(
    currentWeatherData: WeatherData?,
    weatherData: WeatherData,
    weatherUnit: WeatherUnit,
    modifier: Modifier = Modifier,
) {
    val formattedTime = remember(weatherData) { weatherData.time.format(DateTimeFormatter.ofPattern("HH:mm")) }
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (currentWeatherData == weatherData) stringResource(R.string.now) else formattedTime,
            )
            Image(
                painter = painterResource(id = weatherData.weatherType.iconRes),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )
            Text(
                text = "${weatherData.temperatureCelsius}${weatherUnit.temperature}",
                fontWeight = FontWeight.Bold
            )
        }
    }
}