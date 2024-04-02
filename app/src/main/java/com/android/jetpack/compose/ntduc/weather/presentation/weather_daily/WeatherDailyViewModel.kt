package com.android.jetpack.compose.ntduc.weather.presentation.weather_daily

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherData
import com.android.jetpack.compose.ntduc.weather.presentation.weather_home.WeatherDailyState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDailyViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(WeatherDailyState())
        private set

    fun setDayOfYearSelected(dayOfYear: Int, weatherData: Map<Int, List<WeatherData>>) {
        viewModelScope.launch {
            state = state.copy(
                dayOfYearSelected = dayOfYear,
                weatherDataPerDay = weatherData
            )
        }
    }

    fun setDayOfYearSelected(dayOfYear: Int) {
        viewModelScope.launch {
            state = state.copy(
                dayOfYearSelected = dayOfYear,
            )
        }
    }
}