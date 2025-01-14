package com.acon.core.designsystem.component.snackbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconColors
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun AconTextSnackBar(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
    ) {
        AconSnackBar {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = message,
                    style = AconTheme.typography.body3_13_reg,
                    color = AconColors.White
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAconTextSnackBar() {
    AconTextSnackBar(message = "토스트메세지가 작성됩니다.")
}

@Preview
@Composable
fun PreviewAconTextSnackBar1() {
    AconTextSnackBar(message = "2025.01.07 마케팅 수신에 거부하셨습니다.")
}
