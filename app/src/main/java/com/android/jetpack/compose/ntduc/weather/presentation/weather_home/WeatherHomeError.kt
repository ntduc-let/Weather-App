package com.android.jetpack.compose.ntduc.weather.presentation.weather_home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.jetpack.compose.ntduc.weather.R
import com.android.jetpack.compose.ntduc.weather.domain.location.LocationError

@Composable
fun WeatherError(error: LocationError, modifier: Modifier, onRequestLocationPermission: () -> Unit, onEnableGps: () -> Unit, onReload: () -> Unit) {
    Card(modifier = modifier.padding(32.dp)) {
        Text(
            text = if (error is LocationError.OtherError && error.message != null) error.message else stringResource(id = error.errorRes),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
        )

        Button(
            onClick = {
                when (error) {
                    LocationError.GpsError -> onEnableGps.invoke()
                    LocationError.LocationPermissionError -> onRequestLocationPermission.invoke()
                    is LocationError.OtherError -> onReload.invoke()
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(
                text = when (error) {
                    LocationError.GpsError -> stringResource(R.string.enable_current_location)
                    LocationError.LocationPermissionError -> stringResource(R.string.request_permission)
                    is LocationError.OtherError -> stringResource(R.string.reload)
                }
            )
        }
    }
}