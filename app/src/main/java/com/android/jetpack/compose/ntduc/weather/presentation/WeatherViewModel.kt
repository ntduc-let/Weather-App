package com.android.jetpack.compose.ntduc.weather.presentation

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.jetpack.compose.ntduc.weather.R
import com.android.jetpack.compose.ntduc.weather.domain.location.LocationTracker
import com.android.jetpack.compose.ntduc.weather.domain.repository.WeatherRepository
import com.android.jetpack.compose.ntduc.weather.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo(context: Context) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation().let { locationData ->
                val location = locationData.location
                if (location != null) {
                    when (val result = repository.getWeatherData(location.latitude, location.longitude)) {
                        is Resource.Success -> {
                            state = state.copy(
                                weatherInfo = result.data,
                                isLoading = false,
                                error = null
                            )
                        }

                        is Resource.Error -> {
                            state = state.copy(
                                weatherInfo = null,
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                } else {
                    state = state.copy(
                        isLoading = false,
                        error = locationData.error ?: context.getString(R.string.an_error_occurred_please_try_again)
                    )
                }
            }
        }
    }
}