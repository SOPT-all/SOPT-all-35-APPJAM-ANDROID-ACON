package com.acon.feature.onboarding.screen.PreferredFoodRateScreen.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.component.dialog.AconTwoButtonDialog
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.onboarding.component.OnboardingTopBar
import com.acon.feature.onboarding.component.PreferFoodTypeSelectGrid
import com.acon.feature.onboarding.screen.PreferredFoodRateScreen.PreferredFoodRateScreenViewModel
import com.acon.feature.onboarding.screen.PreferredFoodRateScreen.RatePreferFoodScreenSideEffect
import com.acon.feature.onboarding.screen.PreferredFoodRateScreen.RatePreferFoodScreenState
import com.acon.feature.onboarding.type.FoodTypeItems
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun PreferredFoodRateScreenContainer(
    modifier: Modifier = Modifier,
    viewModel: PreferredFoodRateScreenViewModel = hiltViewModel(),
    navigateToPreviousPage: () -> Unit = {},
    navigateToNextPage: () -> Unit = {},
    navigateToLastLoadingPage: () -> Unit = {}
){
    val state = viewModel.collectAsState().value

    PreferredFoodRateScreen(
        modifier = modifier,
        screenState = state,
        columnSize = 3,
        onCardClicked = viewModel::onCardClicked,
        onSkipClicked = viewModel::showDialog,
        navigateToPreviousPage = viewModel::navigateToPreviousPage,
        navigateToNextPage = viewModel::navigateToNextPage,
    )

    viewModel.collectSideEffect {
        when(it){
            RatePreferFoodScreenSideEffect.NavigateToNextPage -> {
                navigateToNextPage()
            }
            RatePreferFoodScreenSideEffect.NavigateToPreviousPage -> {
                navigateToPreviousPage()
            }
        }
    }

    if (state.openCloseDialog) {
        AconTwoButtonDialog(
            title = "취향분석을 그만둘까요?",
            content = "선호도 조사만이 남아있어요!\n1분 내로 빠르게 끝내실 수 있어요.",
            leftButtonContent = "그만두기",
            rightButtonContent = "계속하기",
            contentImage = 0,
            onDismissRequest = {
                viewModel.hideDialog()
            },
            onClickLeft = { // 그만두기
                navigateToLastLoadingPage()
            },
            onClickRight = { // 계속하기
                viewModel.hideDialog()
            },
            isImageEnabled = false
        )
    }

}

@Composable
fun PreferredFoodRateScreen(
    modifier: Modifier = Modifier,
    screenState: RatePreferFoodScreenState,
    columnSize: Int,
    onCardClicked: (String) -> Unit,
    onSkipClicked: () -> Unit,
    navigateToPreviousPage: () -> Unit,
    navigateToNextPage: () -> Unit
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = AconTheme.color.Gray9)
    ){

        OnboardingTopBar(
            totalPages = screenState.totalPages,
            currentPage = screenState.currentPage,
            onLeadingIconClicked = {
                navigateToPreviousPage()
            },
            onTrailingIconClicked = {
                onSkipClicked()
            }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 50.dp, bottom = 50.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ){
                Column(
                    modifier = Modifier
                ){
                    Text(
                        text = "02",
                        color = AconTheme.color.Gray5,
                        style = AconTheme.typography.head4_24_sb,
                        modifier = modifier.padding(vertical = 7.dp)
                    )
                    Text(
                        text = "선호 음식 Top3까지 순위를 매겨주세요.",
                        color = Color.White,
                        style = AconTheme.typography.head6_20_sb,
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ){
                PreferFoodTypeSelectGrid(
                    modifier = modifier
                        .background(Color(0x00000000)),
                    columnSize = columnSize,
                    foodItems = FoodTypeItems.entries.toTypedArray(),
                    onCardClicked = { text ->
                        onCardClicked(text)
                    },
                    selectedCard = screenState.selectedCard,
                    isAllClicked = screenState.selectedCard.size == 3
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ){

                AconFilledLargeButton(
                    text = "다음",
                    textStyle = AconTheme.typography.head8_16_sb,
                    textColor = AconTheme.color.White,
                    enabledBackgroundColor = AconTheme.color.Gray5,
                    disabledBackgroundColor =  AconTheme.color.Gray8,
                    isEnabled = ( screenState.selectedCard.size == 3 ),
                    cornerRadius = 6.dp,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                    onClick = { navigateToNextPage() }
                )
            }
        }
    }

}

@Composable
@Preview
fun PreviewOnboardingScreen2(){
    PreferredFoodRateScreenContainer()
}