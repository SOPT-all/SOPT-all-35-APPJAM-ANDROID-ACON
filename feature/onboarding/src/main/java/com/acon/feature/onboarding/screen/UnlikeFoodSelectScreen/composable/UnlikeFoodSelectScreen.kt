package com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.composable

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
import com.acon.core.designsystem.component.button.AconFilledLargeButton
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.onboarding.component.FoodGrid
import com.acon.feature.onboarding.component.OnboardingTopBar
import com.acon.feature.onboarding.screen.UnlikeFoodSelectScreen.UnlikeFoodScreenState
import com.acon.feature.onboarding.type.FoodItems

/*
* TODO: 탑바 다음 버튼 버그 (없음 누르고 취소하면 안없어짐)
*/

@Composable
fun UnlikeFoodScreen(
    modifier: Modifier = Modifier,
    screenState: UnlikeFoodScreenState,
    columnSize : Int,
    onCardClicked: (String) -> Unit,
    onBackClicked: () -> Unit = {},
    onSkipClicked: () -> Unit = {},
    navigateToNextPage: () -> Unit,
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
                onBackClicked()
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
                        text = "01",
                        color = AconTheme.color.Gray5,
                        style = AconTheme.typography.head4_24_sb,
                        modifier = modifier.padding(vertical = 7.dp)
                    )
                    Text(
                        text = "어떤 음식을 피하고 싶으신가요?",
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
                FoodGrid(
                    modifier = modifier
                        .background(Color(0x00000000)),
                    columnSize = columnSize,
                    foodItems = FoodItems.entries.toTypedArray(),
                    onCardClicked = { text ->
                        onCardClicked(text)
                    },
                    isNothingClicked = screenState.isNothingClicked,
                    selectedCard = screenState.selectedCard
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
                    isEnabled = (screenState.selectedCard.size >= 1),
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
fun PreviewOnboardingScreen(){
    UnlikeFoodScreenContainer(
    )
}
