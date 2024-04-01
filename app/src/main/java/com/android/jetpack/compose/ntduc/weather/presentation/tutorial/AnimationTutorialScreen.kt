package com.android.jetpack.compose.ntduc.weather.presentation.tutorial

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun AnimationTutorialScreen(
    rawRes: Int,
    modifier: Modifier
) {
    val animationComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(rawRes))
    LottieAnimation(
        composition = animationComposition,
        contentScale = ContentScale.Inside,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}