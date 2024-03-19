package com.android.jetpack.compose.ntduc.weather.presentation

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        tutorialVM.onEvent(TutorialEvent.NextTutorial)
        weatherVM.loadWeatherInfo()
    }

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        tutorialVM.onEvent(TutorialEvent.NextTutorial)
    }

    private val locationPermissionSettingLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        tutorialVM.onEvent(TutorialEvent.NextTutorial)
        weatherVM.loadWeatherInfo()
    }

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
                                    requestLocationPermission()
                                },
                                onEnableGps = {
                                    enableGps()
                                },
                            )
                        }
                    }
                }
            }
        }
    }

    private fun requestLocationPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
            || shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            goToLocationPermissionSetting()
        } else {
            locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                )
            )
        }
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            tutorialVM.onEvent(TutorialEvent.NextTutorial)
        }
    }

    private fun goToLocationPermissionSetting() {
        try {
            locationPermissionSettingLauncher.launch(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName")))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun enableGps() {
        try {
            locationPermissionSettingLauncher.launch(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}