package com.android.jetpack.compose.ntduc.weather.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun WeatherError(error: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = error,
            color = Color.Red,
            textAlign = TextAlign.Center
        )
    }
}