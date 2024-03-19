package com.android.jetpack.compose.ntduc.weather.presentation.tutorial

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun TutorialScreen(viewModel: TutorialViewModel, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Image(painter = painterResource(id = viewModel.state.value.currentTutorial.iconRes), contentDescription = null)
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = stringResource(id = viewModel.state.value.currentTutorial.desTitleRes))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = stringResource(id = viewModel.state.value.currentTutorial.titleContentRes))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = stringResource(id = viewModel.state.value.currentTutorial.desContentRes))
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onClick = {
                viewModel.onEvent(TutorialEvent.NextTutorial)
            }
        ) {
            Text(text = stringResource(id = viewModel.state.value.currentTutorial.titleButtonRes))
        }
    }
}