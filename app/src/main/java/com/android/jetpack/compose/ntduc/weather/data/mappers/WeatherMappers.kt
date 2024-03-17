package com.android.jetpack.compose.ntduc.weather.data.mappers

import com.android.jetpack.compose.ntduc.weather.data.remote.WeatherDataDto
import com.android.jetpack.compose.ntduc.weather.data.remote.WeatherDto
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherData
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherInfo
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherType
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherUnit
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy { indexedWeatherData ->
        indexedWeatherData.index / 24
    }.mapValues { map ->
        map.value.map { indexedWeatherData -> indexedWeatherData.data }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    val weatherUnit = WeatherUnit(
        time = weatherUnit.time,
        temperature = weatherUnit.temperature,
        humidity = weatherUnit.humidity,
        pressure = weatherUnit.pressure,
        windSpeed = weatherUnit.windSpeed,
        weatherCode = weatherUnit.weatherCode
    )
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData,
        weatherUnit = weatherUnit
    )
}