package com.android.jetpack.compose.ntduc.weather.domain.location

interface LocationTracker {
    suspend fun getCurrentLocation(): LocationData
}