package com.android.jetpack.compose.ntduc.weather.presentation.weather_home.daily_forecast

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.android.jetpack.compose.ntduc.weather.R
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherDataMaxMin
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherInfo
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun DailyWeatherDisplay(
    modifier: Modifier = Modifier,
    weatherInfo: WeatherInfo,
    weatherData: WeatherDataMaxMin,
    onClickDailyWeather: () -> Unit
) {
    val formattedTime = remember(weatherData) { weatherData.time.format(DateTimeFormatter.ofPattern("EEE dd/MM", Locale.ENGLISH)) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {

            }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            if (weatherInfo.currentWeatherData?.time?.dayOfYear == weatherData.time.dayOfYear) {
                Text(text = stringResource(id = R.string.now), modifier = Modifier.weight(1f))
            } else {
                Text(text = formattedTime, modifier = Modifier.weight(1f))
            }

            WeatherDataMaxMinDisplay(
                valueMax = weatherData.temperatureCelsiusMax.roundToInt(),
                valueMin = weatherData.temperatureCelsiusMin.roundToInt(),
                unit = weatherInfo.weatherUnit.temperature,
                icon = ImageVector.vectorResource(id = R.drawable.ic_temperature),
                modifier = Modifier.padding(start = 16.dp)
            )

            WeatherDataMaxMinDisplay(
                valueMax = weatherData.humidityMax.roundToInt(),
                valueMin = weatherData.humidityMin.roundToInt(),
                unit = weatherInfo.weatherUnit.humidity,
                icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                modifier = Modifier.padding(start = 16.dp)
            )
        }
    }
}