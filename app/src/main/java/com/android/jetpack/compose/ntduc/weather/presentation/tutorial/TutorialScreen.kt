package com.android.jetpack.compose.ntduc.weather.presentation.tutorial

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TutorialScreen(viewModel: TutorialViewModel, modifier: Modifier, onRequestLocationPermission: () -> Unit, onRequestNotificationPermission: () -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimationTutorialScreen(
            rawRes = viewModel.state.value.currentTutorial.animationRes,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
        )

        ContentTutorialScreen(
            viewModel = viewModel,
            modifier = Modifier.weight(0.7f),
            onRequestLocationPermission = onRequestLocationPermission,
            onRequestNotificationPermission = onRequestNotificationPermission
        )
    }
}