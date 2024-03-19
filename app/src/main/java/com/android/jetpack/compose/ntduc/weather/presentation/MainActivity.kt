package com.android.jetpack.compose.ntduc.weather.presentation

import android.Manifest
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

    private val permissionLauncher: ActivityResultLauncher<Array<String>> by lazy {
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            weatherVM.loadWeatherInfo(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestLocationPermission()

        setContent {
            WeatherAppTheme(darkTheme = true) {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = if (tutorialVM.state.value.isShowTutorial) Screen.TutorialScreen.route else Screen.WeatherHomeScreen.route
                    ) {
                        composable(route = Screen.TutorialScreen.route) {
                            TutorialScreen(viewModel = tutorialVM, modifier = Modifier.fillMaxSize())
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
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
    }
}