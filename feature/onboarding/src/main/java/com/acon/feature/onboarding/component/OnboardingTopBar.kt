package com.acon.feature.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.R
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun OnboardingTopBar(
    modifier: Modifier = Modifier,
    totalPages: Int,
    currentPage: Int,
    onLeadingIconClicked: () -> Unit = {},
    onTrailingIconClicked: () -> Unit = {},
    leadingIcon: @Composable () -> Unit = {
        Image (
            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_left_24),
            contentDescription = "Back",
            modifier = Modifier
                .padding(start = 15.dp),
        )
    },
    content: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {
        SkipButton(
            onClickSkipButton = onTrailingIconClicked
        )
    },
){

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(AconTheme.color.Gray9)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(AconTheme.color.Gray9)
                .height(44.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(AconTheme.color.Gray9)
                .height(56.dp),
        ){

            if ( currentPage != 0 ) {
                Box(
                    modifier = Modifier
                        .clickable { onLeadingIconClicked() }
                        .align(Alignment.CenterStart)
                ){
                    leadingIcon()
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 15.dp)
            ){
                trailingIcon()
            }
        }

        OnboardingProgressIndicator(
            modifier = Modifier,
            totalPages = totalPages,
            currentPage = currentPage,
        )
    }
}


@Composable
@Preview
fun OnboardingTopBarPreivew(){
    OnboardingTopBar(
        totalPages = 6,
        currentPage = 4,
    )
}