package com.android.jetpack.compose.ntduc.weather.domain.tutorial

import com.android.jetpack.compose.ntduc.weather.R

sealed class Tutorial(
    val step: Int,
    val animationRes: Int,
    val iconRes: Int,
    val desTitleRes: Int,
    val titleContentRes: Int,
    val desContentRes: Int,
    val titleButtonRes: Int,
    val action: TutorialAction
) {

    data object BeginTutorial : Tutorial(
        step = 0,
        animationRes = R.raw.animation_begin_tutorial,
        iconRes = R.drawable.ic_begin_tutorial_24dp,
        desTitleRes = R.string.welcome,
        titleContentRes = R.string.come_in_the_weather_is,
        desContentRes = R.string.get_your_weather_information_when_and_how_you_want_it_wherever_you_are_with_hyper_local_forecasts_advanced_radar_and_severe_weather_alerts_we_make_life_safer_easier_and_better_start_your_setup_in_3_easy_steps,
        titleButtonRes = R.string.get_started,
        action = TutorialAction.TutorialActionNext
    )

    data object FirstTutorial : Tutorial(
        step = 1,
        animationRes = R.raw.animation_first_tutorial,
        iconRes = R.drawable.ic_first_tutorial_24dp,
        desTitleRes = R.string.terms_privacy,
        titleContentRes = R.string.stay_updated_stay_safe,
        desContentRes = R.string.we_take_tour_privacy_very_seriously_tap_the_arrow_below_to_agree_to_our_terms_of_use_and_privacy_policy_which_will_allow_you_ti_safely_enjoy_the_weather_app,
        titleButtonRes = R.string.agree_and_continue,
        action = TutorialAction.TutorialActionNext
    )

    data object SecondTutorial : Tutorial(
        step = 2,
        animationRes = R.raw.animation_second_tutorial,
        iconRes = R.drawable.ic_second_tutorial_24dp,
        desTitleRes = R.string.location_permissions,
        titleContentRes = R.string.get_hyper_local_forecasts_wherever_you_are,
        desContentRes = R.string.enable_location_services_to_receive_hyper_accurate_forecasts_severe_weather_alerts_news_and_stories_up_to_the_minute_you_can_change_permissions_at_any_time,
        titleButtonRes = R.string.next,
        action = TutorialAction.TutorialActionRequestLocationPermission
    )

    data object ThirdTutorial : Tutorial(
        step = 3,
        animationRes = R.raw.animation_third_tutorial,
        iconRes = R.drawable.ic_third_tutorial_24dp,
        desTitleRes = R.string.notifications,
        titleContentRes = R.string.stay_ahead_the_storm,
        desContentRes = R.string.turn_on_notifications_to_receive_severe_weather_alerts_and_breaking_news_for_your_saved_locations_so_you_re_always_prepared,
        titleButtonRes = R.string.next,
        action = TutorialAction.TutorialActionRequestNotificationPermission
    )
}