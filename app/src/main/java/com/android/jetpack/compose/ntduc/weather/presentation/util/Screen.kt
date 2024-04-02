package com.android.jetpack.compose.ntduc.weather.presentation.util

sealed class Screen(val route: String) {
    data object TutorialScreen: Screen("tutorial_screen")
    data object WeatherHomeScreen: Screen("weather_home_screen")
    data object WeatherDailyScreen: Screen("weather_daily_screen")
}