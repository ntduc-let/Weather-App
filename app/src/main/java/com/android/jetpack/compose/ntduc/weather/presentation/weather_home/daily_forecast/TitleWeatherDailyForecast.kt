package com.android.jetpack.compose.ntduc.weather.presentation.weather_home.daily_forecast

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.jetpack.compose.ntduc.weather.R

@Composable
fun TitleWeatherDailyForecast() {
    Spacer(modifier = Modifier.height(32.dp))

    Text(
        text = stringResource(R.string.daily_forecast),
        fontSize = 20.sp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}