package com.acon.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconTheme

@Composable
fun AconChip(
    title: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = AconTheme.typography.body3_13_reg.copy(
        color = AconTheme.color.White
    )
) {

    val borderColor =
        if (selected)
            AconTheme.color.Main_org1
        else AconTheme.color.Gray6

    val containerColor =
        if (selected)
            AconTheme.color.Main_org35
        else AconTheme.color.Gray8

    Row(
        modifier = modifier
            .clip(CircleShape)
            .border(
                shape = CircleShape,
                width = 1.dp,
                color = borderColor
            ).background(containerColor).clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 7.dp, horizontal = 16.dp),
            text = title,
            style = textStyle
        )
    }
}

@Preview
@Composable
private fun AconChipPreview() {
    AconChip(
        title = "한식",
        selected = false,
        onClick = {}
    )
}

@Preview
@Composable
private fun SelectedAconChipPreview() {
    AconChip(
        title = "한식",
        selected = true,
        onClick = {}
    )
}