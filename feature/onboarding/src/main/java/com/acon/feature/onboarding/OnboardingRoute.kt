package com.acon.feature.onboarding

import com.acon.feature.onboarding.screen.OnboardingScreen.OnboardingResult
import kotlinx.serialization.Serializable

sealed interface OnboardingRoute {

    @Serializable
    data object Graph : OnboardingRoute

    @Serializable
    data object OnboardingScreen : OnboardingRoute

    @Serializable
    data object LastLoading : OnboardingRoute
}