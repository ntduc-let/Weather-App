package com.android.jetpack.compose.ntduc.weather.domain.tutorial

sealed class TutorialAction {
    data object TutorialActionNext : TutorialAction()

    data object TutorialActionRequestLocationPermission : TutorialAction()

    data object TutorialActionRequestNotificationPermission : TutorialAction()
}