package com.android.jetpack.compose.ntduc.weather.presentation.weather_home.daily_forecast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.jetpack.compose.ntduc.weather.R

@Composable
fun WeatherDataMaxMinDisplay(
    valueMax: Int,
    valueMin: Int,
    unit: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(25.dp),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = String.format(stringResource(R.string.max_s), "$valueMax$unit"),
            )
            Text(
                text = String.format(stringResource(R.string.min_s), "$valueMin$unit"),
            )
        }

    }
}