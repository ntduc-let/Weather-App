package com.android.jetpack.compose.ntduc.weather.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.jetpack.compose.ntduc.weather.presentation.tutorial.TutorialEvent
import com.android.jetpack.compose.ntduc.weather.presentation.tutorial.TutorialScreen
import com.android.jetpack.compose.ntduc.weather.presentation.tutorial.TutorialViewModel
import com.android.jetpack.compose.ntduc.weather.presentation.util.Argument
import com.android.jetpack.compose.ntduc.weather.presentation.util.Screen
import com.android.jetpack.compose.ntduc.weather.presentation.weather_daily.WeatherDailyScreen
import com.android.jetpack.compose.ntduc.weather.presentation.weather_daily.WeatherDailyViewModel
import com.android.jetpack.compose.ntduc.weather.presentation.weather_home.WeatherHomeScreen
import com.android.jetpack.compose.ntduc.weather.presentation.weather_home.WeatherHomeViewModel
import com.android.jetpack.compose.ntduc.weather.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivityPermission() {

    private val weatherVM: WeatherHomeViewModel by viewModels()
    private val tutorialVM: TutorialViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WeatherAppTheme(darkTheme = true) {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = if (tutorialVM.state.value.isShowTutorial) Screen.TutorialScreen.route else Screen.WeatherHomeScreen.route
                    ) {
                        composable(route = Screen.TutorialScreen.route) {
                            TutorialScreen(
                                viewModel = tutorialVM,
                                modifier = Modifier.fillMaxSize(),
                                onRequestLocationPermission = {
                                    requestLocationPermission()
                                },
                                onRequestNotificationPermission = {
                                    requestNotificationPermission()
                                }
                            )
                        }

                        composable(route = Screen.WeatherHomeScreen.route) {
                            WeatherHomeScreen(
                                viewModel = weatherVM,
                                modifier = Modifier.fillMaxSize(),
                                onRequestLocationPermission = {
                                    requestLocationPermissionAndReloadData()
                                },
                                onEnableGps = {
                                    enableGps()
                                },
                                onClickDailyWeather = { dayOfYear ->
                                    navController.navigate(route = "${Screen.WeatherDailyScreen.route}/$dayOfYear")
                                }
                            )
                        }

                        composable(
                            route = "${Screen.WeatherDailyScreen.route}/{${Argument.DayOfYear.key}}",
                            arguments = listOf(
                                navArgument(Argument.DayOfYear.key) {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            val dayOfYear = remember {
                                it.arguments?.getInt(Argument.DayOfYear.key)
                            }
                            dayOfYear?.let { day ->
                                val weatherDataPerDay = weatherVM.state.weatherInfo?.weatherDataPerDay ?: return@composable
                                val weatherDailyViewModel = hiltViewModel<WeatherDailyViewModel>(key = dayOfYear.toString())
                                weatherDailyViewModel.setDayOfYearSelected(dayOfYear = dayOfYear, weatherData = weatherDataPerDay)

                                WeatherDailyScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    viewModel = weatherDailyViewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun nextTutorial() {
        super.nextTutorial()
        tutorialVM.onEvent(TutorialEvent.NextTutorial)
    }

    override fun reloadWeatherInfo() {
        super.reloadWeatherInfo()
        weatherVM.loadWeatherInfo()
    }
}