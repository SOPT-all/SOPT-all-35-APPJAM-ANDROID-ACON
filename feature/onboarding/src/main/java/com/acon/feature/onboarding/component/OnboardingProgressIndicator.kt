package com.acon.feature.onboarding.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconTheme


@Composable
fun OnboardingProgressIndicator(
    modifier: Modifier = Modifier,
    currentPage: Int,
    totalPages: Int
) {
    LinearProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .height(2.dp),
        progress = { currentPage / totalPages.toFloat() },
        color = AconTheme.color.Main_org1,
        trackColor = AconTheme.color.Gray8,
        strokeCap = StrokeCap.Square,
        gapSize = 0.dp,
        drawStopIndicator = {}
    )
}


@Preview
@Composable
fun previewOnboardingProcessIndicator(){
    OnboardingProgressIndicator(
        totalPages = 6,
        currentPage = 4
    )
}