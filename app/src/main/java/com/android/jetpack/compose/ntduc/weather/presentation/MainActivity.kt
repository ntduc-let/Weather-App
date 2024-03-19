package com.android.jetpack.compose.ntduc.weather.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.jetpack.compose.ntduc.weather.presentation.tutorial.TutorialEvent
import com.android.jetpack.compose.ntduc.weather.presentation.tutorial.TutorialScreen
import com.android.jetpack.compose.ntduc.weather.presentation.tutorial.TutorialViewModel
import com.android.jetpack.compose.ntduc.weather.presentation.util.Screen
import com.android.jetpack.compose.ntduc.weather.presentation.weather_home.WeatherHomeScreen
import com.android.jetpack.compose.ntduc.weather.presentation.weather_home.WeatherViewModel
import com.android.jetpack.compose.ntduc.weather.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherVM: WeatherViewModel by viewModels()
    private val tutorialVM: TutorialViewModel by viewModels()

    private lateinit var locationPermissionLauncher: ActivityResultLauncher<Array<String>>

    private lateinit var notificationPermissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            tutorialVM.onEvent(TutorialEvent.NextTutorial)
            weatherVM.loadWeatherInfo(this)
        }

        notificationPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            tutorialVM.onEvent(TutorialEvent.NextTutorial)
        }

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
                            WeatherHomeScreen(viewModel = weatherVM, modifier = Modifier.fillMaxSize())
                        }
                    }
                }
            }
        }
    }

    private fun requestLocationPermission() {
        locationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.POST_NOTIFICATIONS,
                )
            )
        } else {
            tutorialVM.onEvent(TutorialEvent.NextTutorial)
        }
    }
}