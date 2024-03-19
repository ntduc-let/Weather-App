package com.android.jetpack.compose.ntduc.weather.presentation.tutorial

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.android.jetpack.compose.ntduc.weather.domain.tutorial.Tutorial
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TutorialViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(TutorialState())
    val state: State<TutorialState> = _state

    fun onEvent(event: TutorialEvent) {
        when (event) {
            is TutorialEvent.NextTutorial -> {
                when (state.value.currentTutorial.step) {
                    Tutorial.BeginTutorial.step -> _state.value = state.value.copy(
                        currentTutorial = Tutorial.FirstTutorial,
                    )

                    Tutorial.FirstTutorial.step -> _state.value = state.value.copy(
                        currentTutorial = Tutorial.SecondTutorial,
                    )

                    Tutorial.SecondTutorial.step -> _state.value = state.value.copy(
                        currentTutorial = Tutorial.ThirdTutorial,
                    )

                    Tutorial.ThirdTutorial.step -> {
                        TutorialState.showedTutorial()
                        _state.value = state.value.copy(
                            isShowTutorial = false
                        )
                    }
                }
            }
        }
    }
}