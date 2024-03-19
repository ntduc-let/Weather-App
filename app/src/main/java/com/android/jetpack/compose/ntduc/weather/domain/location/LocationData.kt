package com.android.jetpack.compose.ntduc.weather.domain.location

import android.location.Location

data class LocationData(
    val location: Location?,
    val error: LocationError?
)