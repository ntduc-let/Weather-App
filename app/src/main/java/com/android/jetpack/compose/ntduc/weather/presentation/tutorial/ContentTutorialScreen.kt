package com.android.jetpack.compose.ntduc.weather.presentation.tutorial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.jetpack.compose.ntduc.weather.domain.tutorial.TutorialAction

@Composable
fun ContentTutorialScreen(viewModel: TutorialViewModel, modifier: Modifier, onRequestLocationPermission: () -> Unit, onRequestNotificationPermission: () -> Unit) {
    Card(
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        modifier = modifier,
    ) {
        val currentTutorial = viewModel.state.value.currentTutorial
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = currentTutorial.iconRes),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .size(56.dp)
            )
            Text(
                text = stringResource(id = currentTutorial.desTitleRes),
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
            Text(
                text = stringResource(id = currentTutorial.titleContentRes),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )
            Text(
                text = stringResource(id = currentTutorial.desContentRes),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                onClick = {
                    when (currentTutorial.action) {
                        TutorialAction.TutorialActionNext -> viewModel.onEvent(TutorialEvent.NextTutorial)
                        TutorialAction.TutorialActionRequestLocationPermission -> onRequestLocationPermission.invoke()
                        TutorialAction.TutorialActionRequestNotificationPermission -> onRequestNotificationPermission.invoke()
                    }
                }
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(text = stringResource(id = viewModel.state.value.currentTutorial.titleButtonRes), modifier = Modifier.align(Alignment.Center))
                    Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowForward, contentDescription = null, modifier = Modifier.align(Alignment.CenterEnd))
                }
            }
        }
    }
}