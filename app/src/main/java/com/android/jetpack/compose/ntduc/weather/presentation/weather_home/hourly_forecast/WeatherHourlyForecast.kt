package com.android.jetpack.compose.ntduc.weather.presentation.weather_home.hourly_forecast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.jetpack.compose.ntduc.weather.R
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherInfo

@Composable
fun WeatherHourlyForecast(
    weatherInfo: WeatherInfo?,
    modifier: Modifier = Modifier
) {
    weatherInfo?.weatherDataHourly?.let { dataList ->
        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.hourly_forecast),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(dataList) { data ->
                    HourlyWeatherDisplay(
                        currentWeatherData = weatherInfo.currentWeatherData,
                        weatherData = data,
                        weatherUnit = weatherInfo.weatherUnit,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}