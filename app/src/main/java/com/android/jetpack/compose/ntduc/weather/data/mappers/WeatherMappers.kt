package com.android.jetpack.compose.ntduc.weather.data.mappers

import com.android.jetpack.compose.ntduc.weather.data.remote.WeatherDataDto
import com.android.jetpack.compose.ntduc.weather.data.remote.WeatherDto
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherData
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherInfo
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherType
import com.android.jetpack.compose.ntduc.weather.domain.weather.WeatherUnit
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun WeatherDataDto.toWeatherData(): List<WeatherData> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        WeatherData(
            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
            temperatureCelsius = temperature,
            pressure = pressure,
            windSpeed = windSpeed,
            humidity = humidity,
            weatherType = WeatherType.fromWMO(weatherCode)
        )
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherUnit = WeatherUnit(
        time = weatherUnit.time,
        temperature = weatherUnit.temperature,
        humidity = weatherUnit.humidity,
        pressure = weatherUnit.pressure,
        windSpeed = weatherUnit.windSpeed,
        weatherCode = weatherUnit.weatherCode
    )

    val now = LocalDateTime.now()

    val weatherData = weatherData.toWeatherData()

    val weatherDataPerDay = weatherData.groupBy {
        it.time.dayOfYear
    }

    val currentWeatherData = weatherData.find {
        var dayOfYear = now.dayOfYear
        var hour = if (now.minute < 30) now.hour else now.hour + 1
        if (hour == 24) {
            hour = 0
            dayOfYear += 1
        }

        it.time.dayOfYear == dayOfYear && it.time.hour == hour
    }

    val currentWeatherDataIndex = weatherData.indexOf(currentWeatherData)
    val weatherDataNext24Hour = weatherData.subList(currentWeatherDataIndex, currentWeatherDataIndex + 24)

    return WeatherInfo(
        weatherDataPerDay = weatherDataPerDay,
        weatherDataNext24Hour = weatherDataNext24Hour,
        currentWeatherData = currentWeatherData,
        weatherUnit = weatherUnit
    )
}