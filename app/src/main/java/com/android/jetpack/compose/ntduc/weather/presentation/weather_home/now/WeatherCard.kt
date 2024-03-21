package com.android.jetpack.compose.ntduc.weather.presentation.weather_home.now

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.jetpack.compose.ntduc.weather.R
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherInfo
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    weatherInfo: WeatherInfo?,
    modifier: Modifier = Modifier
) {
    weatherInfo?.currentWeatherData?.let { data ->
        Card(
            modifier = modifier
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = String.format(stringResource(R.string.today_s), data.time.format(DateTimeFormatter.ofPattern("HH:mm"))),
                    modifier = Modifier.align(Alignment.End),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(200.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${data.temperatureCelsius}${weatherInfo.weatherUnit.temperature}",
                    fontSize = 50.sp,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(id = data.weatherType.descRes),
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = data.pressure.roundToInt(),
                        unit = weatherInfo.weatherUnit.pressure,
                        icon = ImageVector.vectorResource(id = R.drawable.ic_pressure),
                    )
                    WeatherDataDisplay(
                        value = data.humidity.roundToInt(),
                        unit = weatherInfo.weatherUnit.humidity,
                        icon = ImageVector.vectorResource(id = R.drawable.ic_drop),
                    )
                    WeatherDataDisplay(
                        value = data.windSpeed.roundToInt(),
                        unit = weatherInfo.weatherUnit.windSpeed,
                        icon = ImageVector.vectorResource(id = R.drawable.ic_wind),
                    )
                }
            }
        }
    }
}