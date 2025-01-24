package com.acon.feature.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.noRippleClickable
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.onboarding.type.CardItem
import com.acon.feature.onboarding.type.PlaceItems

@Composable
fun <T : CardItem> FreqPlaceSelectGrid(
    modifier : Modifier = Modifier,
    columnSize : Int,
    placeItems: Array<T>,
    onCardClicked: (String) -> Unit,
    selectedCard: Set<String>,
){
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(columnSize),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ){
        items(placeItems) { place ->
            PlaceCard(
                imageRes = place.imageResId,
                text = place.cardName,
                id = place.id,
                selected = (selectedCard.contains(place.id)),
                onCardClicked = { id ->
                    onCardClicked(id)
                },
            )
        }
    }
}

@Composable
fun PlaceCard(
    modifier: Modifier = Modifier,
    imageRes: Int,
    text: String,
    id: String,
    selected: Boolean,
    onCardClicked: (String) -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(6.dp))
                .aspectRatio(0.5f)
                .noRippleClickable {
                onCardClicked(id)
            },
            contentAlignment = Alignment.Center
        ){
            // 버튼 하나씩
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = text,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            if (selected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(6.dp))
                        .background(AconTheme.color.Dim_g_30)
                )
                Image(
                    imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_check_44),
                    contentDescription = "Clicked",
                    modifier = Modifier.size(48.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = text,
            color = AconTheme.color.White,
            style = AconTheme.typography.subtitle2_14_med
        )
    }
}

@Preview
@Composable
private fun PreviewFreqPlaceSelectGrid() {

    val selectedCard = remember { mutableStateOf(setOf<String>()) }

    Column {
        FreqPlaceSelectGrid(
            columnSize = 2,
            placeItems = PlaceItems.entries.toTypedArray(),
            onCardClicked = { it ->
                if(selectedCard.value.isEmpty()) selectedCard.value += it
                else if (selectedCard.value.contains(it)) selectedCard.value -= it
                else {
                    selectedCard.value = emptySet()
                    selectedCard.value += it
                }
            },
            selectedCard = selectedCard.value
        )
    }
}