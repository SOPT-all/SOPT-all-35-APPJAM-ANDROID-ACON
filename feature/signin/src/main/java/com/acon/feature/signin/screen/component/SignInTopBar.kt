package com.acon.feature.signin.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun SignInTopBar(
    modifier: Modifier = Modifier,
    onClickText: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .background(AconTheme.color.Black)
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = stringResource(com.acon.feature.signin.R.string.signin_topbar_text),
            style = AconTheme.typography.body2_14_reg,
            color = AconTheme.color.White,
            modifier = Modifier
                .padding(end = 20.dp)
                .clickable { onClickText() }
        )
    }
}


@Composable
@Preview(showBackground = true)
private fun SignInTopBarPreview() {
    AconTheme {
        SignInTopBar()
    }
}