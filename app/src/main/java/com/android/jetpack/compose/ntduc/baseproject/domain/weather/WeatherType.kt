package com.android.jetpack.compose.ntduc.baseproject.domain.weather

import androidx.annotation.DrawableRes
import com.android.jetpack.compose.ntduc.baseproject.R

sealed class WeatherType(
    val descRes: Int,
    @DrawableRes val iconRes: Int
) {
    data object ClearSky : WeatherType(
        descRes = R.string.clear_sky,
        iconRes = R.drawable.ic_sunny
    )

    data object MainlyClear : WeatherType(
        descRes = R.string.mainly_clear,
        iconRes = R.drawable.ic_cloudy
    )

    data object PartlyCloudy : WeatherType(
        descRes = R.string.partly_cloudy,
        iconRes = R.drawable.ic_cloudy
    )

    data object Overcast : WeatherType(
        descRes = R.string.overcast,
        iconRes = R.drawable.ic_cloudy
    )

    data object Foggy : WeatherType(
        descRes = R.string.foggy,
        iconRes = R.drawable.ic_very_cloudy
    )

    data object DepositingRimeFog : WeatherType(
        descRes = R.string.depositing_rime_fog,
        iconRes = R.drawable.ic_very_cloudy
    )

    data object LightDrizzle : WeatherType(
        descRes = R.string.light_drizzle,
        iconRes = R.drawable.ic_rainshower
    )

    data object ModerateDrizzle : WeatherType(
        descRes = R.string.moderate_drizzle,
        iconRes = R.drawable.ic_rainshower
    )

    data object DenseDrizzle : WeatherType(
        descRes = R.string.dense_drizzle,
        iconRes = R.drawable.ic_rainshower
    )

    data object LightFreezingDrizzle : WeatherType(
        descRes = R.string.slight_freezing_drizzle,
        iconRes = R.drawable.ic_snowyrainy
    )

    data object DenseFreezingDrizzle : WeatherType(
        descRes = R.string.dense_freezing_drizzle,
        iconRes = R.drawable.ic_snowyrainy
    )

    data object SlightRain : WeatherType(
        descRes = R.string.slight_rain,
        iconRes = R.drawable.ic_rainy
    )

    data object ModerateRain : WeatherType(
        descRes = R.string.rainy,
        iconRes = R.drawable.ic_rainy
    )

    data object HeavyRain : WeatherType(
        descRes = R.string.heavy_rain,
        iconRes = R.drawable.ic_rainy
    )

    data object HeavyFreezingRain : WeatherType(
        descRes = R.string.heavy_freezing_rain,
        iconRes = R.drawable.ic_snowyrainy
    )

    data object SlightSnowFall : WeatherType(
        descRes = R.string.slight_snow_fall,
        iconRes = R.drawable.ic_snowy
    )

    data object ModerateSnowFall : WeatherType(
        descRes = R.string.moderate_snow_fall,
        iconRes = R.drawable.ic_heavysnow
    )

    data object HeavySnowFall : WeatherType(
        descRes = R.string.heavy_snow_fall,
        iconRes = R.drawable.ic_heavysnow
    )

    data object SnowGrains : WeatherType(
        descRes = R.string.snow_grains,
        iconRes = R.drawable.ic_heavysnow
    )

    data object SlightRainShowers : WeatherType(
        descRes = R.string.slight_rain_showers,
        iconRes = R.drawable.ic_rainshower
    )

    data object ModerateRainShowers : WeatherType(
        descRes = R.string.moderate_rain_showers,
        iconRes = R.drawable.ic_rainshower
    )

    data object ViolentRainShowers : WeatherType(
        descRes = R.string.violent_rain_showers,
        iconRes = R.drawable.ic_rainshower
    )

    data object SlightSnowShowers : WeatherType(
        descRes = R.string.light_snow_showers,
        iconRes = R.drawable.ic_snowy
    )

    data object HeavySnowShowers : WeatherType(
        descRes = R.string.heavy_snow_showers,
        iconRes = R.drawable.ic_snowy
    )

    data object ModerateThunderstorm : WeatherType(
        descRes = R.string.moderate_thunderstorm,
        iconRes = R.drawable.ic_thunder
    )

    data object SlightHailThunderstorm : WeatherType(
        descRes = R.string.thunderstorm_with_slight_hail,
        iconRes = R.drawable.ic_rainythunder
    )

    data object HeavyHailThunderstorm : WeatherType(
        descRes = R.string.thunderstorm_with_heavy_hail,
        iconRes = R.drawable.ic_rainythunder
    )

    companion object {
        fun fromWMO(code: Int): WeatherType {
            return when (code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}