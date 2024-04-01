package com.android.jetpack.compose.ntduc.weather.presentation

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import com.android.jetpack.compose.ntduc.weather.presentation.tutorial.TutorialEvent
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
open class BaseActivityPermission : ComponentActivity() {

    protected open fun onLocationPermissionRequested() {}
    protected open fun reloadWeatherInfo() {}
    protected open fun onNotificationPermissionRequested() {}


    private val locationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        onLocationPermissionRequested()
    }

    private val locationPermissionSettingLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        onLocationPermissionRequested()
    }

    private val enableGpsSettingLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        reloadWeatherInfo()
    }

    private val locationPermissionAndReloadDataLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {
        reloadWeatherInfo()
    }

    private val locationPermissionSettingAndReloadDataLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        reloadWeatherInfo()
    }

    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        onNotificationPermissionRequested()
    }

    protected fun requestLocationPermissionAndReloadData() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)
            || shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            goToLocationPermissionSettingAndReloadData()
        } else {
            locationPermissionAndReloadDataLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                )
            )
        }
    }

    protected fun requestLocationPermission() {
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

    protected fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            onNotificationPermissionRequested()
        }
    }

    protected fun goToLocationPermissionSetting() {
        try {
            locationPermissionSettingLauncher.launch(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName")))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected fun goToLocationPermissionSettingAndReloadData() {
        try {
            locationPermissionSettingAndReloadDataLauncher.launch(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName")))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected fun enableGps() {
        try {
            enableGpsSettingLauncher.launch(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}