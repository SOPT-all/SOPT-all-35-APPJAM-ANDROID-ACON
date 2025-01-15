package com.acon.acon.navigation.route

import kotlinx.serialization.Serializable

@Serializable
sealed interface OnboardingRoute {

    @Serializable
    data object Graph : OnboardingRoute

    @Serializable
    data object ChooseUnlikeFoods : OnboardingRoute

    @Serializable
    data object RatePreferFoods : OnboardingRoute

    @Serializable
    data object SelectFrequentPlace : OnboardingRoute

    @Serializable
    data object SelectPreferPlace : OnboardingRoute

    @Serializable
    data object RatePreferPlace : OnboardingRoute

    @Serializable
    data object LastLoading : OnboardingRoute
}