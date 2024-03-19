package com.android.jetpack.compose.ntduc.weather.domain.location

import com.android.jetpack.compose.ntduc.weather.R

sealed class LocationError(
    val errorRes: Int
) {
    data object LocationPermissionError : LocationError(
        errorRes = R.string.couldn_t_retrieve_location_make_sure_to_grant_location_permissions
    )

    data object GpsError : LocationError(
        errorRes = R.string.couldn_t_retrieve_location_make_sure_gps_is_turned_on
    )

    data class OtherError(val message: String?) : LocationError(
        errorRes = R.string.an_error_occurred_please_try_again
    )
}