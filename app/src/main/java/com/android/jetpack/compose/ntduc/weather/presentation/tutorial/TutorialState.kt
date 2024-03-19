package com.android.jetpack.compose.ntduc.weather.presentation.tutorial

import com.android.jetpack.compose.ntduc.weather.domain.tutorial.Tutorial
import com.orhanobut.hawk.Hawk

data class TutorialState(
    val isShowTutorial: Boolean = Hawk.get(IS_SHOW_TUTORIAL, true),
    val currentTutorial: Tutorial = Tutorial.BeginTutorial,
) {
    companion object {
        private const val IS_SHOW_TUTORIAL = "IS_SHOW_TUTORIAL"

        fun showedTutorial() {
            Hawk.put(IS_SHOW_TUTORIAL, false)
        }
    }
}