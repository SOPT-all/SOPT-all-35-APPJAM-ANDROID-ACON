package com.acon.feature.onboarding.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun SkipButton(
    modifier: Modifier = Modifier,
    onClickSkipButton: () -> Unit = {}
){
    Text(
        text = "건너뛰기",
        color = AconTheme.color.White,
        style = AconTheme.typography.body2_14_reg,
        modifier = modifier
            .padding(5.dp)
            .clickable {
                onClickSkipButton()
            }
    )

}

@Composable
@Preview
fun SkipButtonPreview(){
    SkipButton()
}