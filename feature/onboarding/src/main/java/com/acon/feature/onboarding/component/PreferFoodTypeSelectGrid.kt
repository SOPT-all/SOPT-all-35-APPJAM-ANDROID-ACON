package com.acon.feature.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.noRippleClickable
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.onboarding.type.CardItem
import com.acon.feature.onboarding.type.FoodItems
import com.acon.feature.onboarding.type.FoodTypeItems
import kotlinx.coroutines.selects.select

@Composable
fun <T : CardItem> PreferFoodTypeSelectGrid(
    modifier : Modifier = Modifier,
    columnSize : Int,
    foodItems: Array<T>,
    onCardClicked: (String) -> Unit,
    isAllClicked: Boolean = false,
    selectedCard: Set<String>,
){
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(columnSize),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ){
        items(foodItems) { food ->
            FoodTypeCard(
                imageRes = food.imageResId,
                text = food.cardName,
                id = food.id,
                selected = (selectedCard.contains(food.id)),
                onCardClicked = { id ->
                    onCardClicked(id)
                },
                isAllClicked = isAllClicked,
                selectedCard = selectedCard,
            )
        }
    }
}

@Composable
fun FoodTypeCard(
    modifier: Modifier = Modifier,
    imageRes: Int,
    text: String,
    id: String,
    selected: Boolean,
    onCardClicked: (String) -> Unit,
    isAllClicked: Boolean,
    selectedCard: Set<String>,
    ) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .noRippleClickable(enabled = selected || !isAllClicked) {
                    onCardClicked(id)
                },
            contentAlignment = Alignment.Center

        ){
            // 버튼 하나씩
                Box(
                    modifier = Modifier.alpha(if (isAllClicked && !selected) 0.1f else 1f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = text,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(RoundedCornerShape(6.dp)).fillMaxSize()
                    )

                    if (selected) { //top 3 이내에 선정된 경우, 체크표시 말고 등수 번호와 함께 선택된 효과
                        val rateIcon = if (selectedCard.indexOf(id) == 0) com.acon.feature.onboarding.R.drawable.ic_1
                                        else if (selectedCard.indexOf(id) == 1) com.acon.feature.onboarding.R.drawable.ic_2
                                        else if (selectedCard.indexOf(id) == 2) com.acon.feature.onboarding.R.drawable.ic_3
                                        else 0
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(6.dp))
                                .background(AconTheme.color.Dim_g_30)
                        )
                        Image(
                            imageVector = ImageVector.vectorResource(rateIcon),
                            contentDescription = "Clicked",
                            modifier = Modifier.size(48.dp)
                        )


                    }
                }

        }
        val cardTextAlpha = if (isAllClicked && !selected) 0.1f else 1f //수정 필요
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.alpha(cardTextAlpha),
            text = text,
            color = AconTheme.color.White,
            style = AconTheme.typography.subtitle2_14_med,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun PreviewFoodTypeGrid() {

    val selectedCard = remember { mutableStateOf(setOf<String>()) }

    Column(

    ){

        PreferFoodTypeSelectGrid(
            columnSize = 3,
            foodItems = FoodTypeItems.entries.toTypedArray(),
            onCardClicked = {
                if(selectedCard.value.contains(it)) selectedCard.value -= it
                else selectedCard.value += it
            },
            isAllClicked = false,
            selectedCard = selectedCard.value
        )
    }
}