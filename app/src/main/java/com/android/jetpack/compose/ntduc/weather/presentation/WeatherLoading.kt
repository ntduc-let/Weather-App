package com.android.jetpack.compose.ntduc.weather.presentation

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WeatherLoading(modifier: Modifier) {
    CircularProgressIndicator(
        modifier = modifier
    )
}