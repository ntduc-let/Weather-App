package com.android.jetpack.compose.ntduc.baseproject.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation(): Location?
}