package com.acon.acon.navigation.nested

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.acon.acon.navigation.route.OnboardingRoute
import com.acon.feature.onboarding.screen.PreferredFoodRateScreen.composable.PreferredFoodRateScreenContainer
import com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.composable.UnlikeFoodScreenContainer

internal fun NavGraphBuilder.onboardingNavigationNavigation(
    navController: NavHostController
) {

    navigation<OnboardingRoute.Graph>(
        startDestination = OnboardingRoute.ChooseUnlikeFoods
    ) {
        composable<OnboardingRoute.ChooseUnlikeFoods> {
            UnlikeFoodScreenContainer(
                navigateToNextPage = {
                    navController.navigate(OnboardingRoute.RatePreferFoods)
                },
                navigateToLastLoadingPage = {
                    navController.navigate(OnboardingRoute.LastLoading)
                }
            )
        }

        composable<OnboardingRoute.RatePreferFoods> {
             PreferredFoodRateScreenContainer(
                 navigateToPreviousPage = {
                     navController.navigate(OnboardingRoute.ChooseUnlikeFoods)
                 },
                 navigateToNextPage = {
                     navController.navigate(OnboardingRoute.SelectFrequentPlace)
                 },
                 navigateToLastLoadingPage = {
                     navController.navigate(OnboardingRoute.LastLoading)
                 }
             )


        }
        composable<OnboardingRoute.SelectFrequentPlace> {
            // FrequentPlaceSelectScreenContainer()
        }
        composable<OnboardingRoute.SelectPreferPlace> {
            // PreferredPlaceSelectScreenContainer()
        }
        composable<OnboardingRoute.RatePreferPlace> {
            // PerferredPlaceRateScreenContainer()
        }
        composable<OnboardingRoute.LastLoading> {

        }
    }
}