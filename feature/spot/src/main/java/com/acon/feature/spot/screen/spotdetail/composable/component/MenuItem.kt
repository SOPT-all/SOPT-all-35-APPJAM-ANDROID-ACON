package com.acon.feature.spot.screen.spotdetail.composable.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.acon.core.designsystem.theme.AconTheme
import com.acon.domain.model.spot.SpotDetailMenu
import com.acon.feature.spot.toPrice

@Composable
fun MenuItem(
    menu: SpotDetailMenu,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(AconTheme.color.Gray9)
            .padding(vertical = 16.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 15.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = menu.name,
                style = AconTheme.typography.body2_14_reg,
                maxLines = 1,
                color = AconTheme.color.White
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = menu.price.toPrice(),
                style = AconTheme.typography.subtitle1_16_med,
                maxLines = 1,
                color = AconTheme.color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
        ) {
            if(menu.image?.isBlank() == true) {
                Image(
                    imageVector = ImageVector.vectorResource(
                        id = com.acon.core.designsystem.R.drawable.ic_store_detail_menu_78),
                    contentDescription = "",
                    )
            } else {
                AsyncImage(
                    model = menu.image,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuItemPreview() {
    AconTheme {
        MenuItem(
            menu = SpotDetailMenu(
                id = 1,
                name = "고양이",
                price = 100000,
                image = ""
            )
        )
    }
}
