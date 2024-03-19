package com.android.jetpack.compose.ntduc.weather.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.android.jetpack.compose.ntduc.weather.domain.location.LocationData
import com.android.jetpack.compose.ntduc.weather.domain.location.LocationError
import com.android.jetpack.compose.ntduc.weather.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

@ExperimentalCoroutinesApi
class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {

    override suspend fun getCurrentLocation(): LocationData {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission) {
            return LocationData(location = null, error = LocationError.LocationPermissionError)
        }

        if (!isGpsEnabled) {
            return LocationData(location = null, error = LocationError.GpsError)
        }

        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        cont.resume(LocationData(location = result, error = null))
                    } else {
                        cont.resume(LocationData(location = null, error = LocationError.OtherError(null)))
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(LocationData(location = it, error = null))
                }
                addOnFailureListener {
                    cont.resume(LocationData(location = null, error = LocationError.OtherError(null)))
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}