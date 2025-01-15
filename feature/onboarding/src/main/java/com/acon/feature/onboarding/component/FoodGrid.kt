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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.acon.core.designsystem.theme.AconTheme
import com.acon.feature.onboarding.type.CardItem
import com.acon.feature.onboarding.type.FoodItems

@Composable
fun <T : CardItem> FoodGrid( //이름을 HateFoodSelectGrid로 수정해야 할 듯.
    modifier : Modifier = Modifier,
    columnSize : Int,
    foodItems: Array<T>,
    onCardClicked: (String) -> Unit,
    isNothingClicked: Boolean = false,
    selectedCard: Set<String>,
){
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(columnSize),
        contentPadding = PaddingValues(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ){
        items(foodItems) { food ->
            FoodCard(
                imageRes = food.imageResId,
                text = food.cardName,
                selected = (selectedCard.contains(food.cardName)),
                onCardClicked = { text ->
                    onCardClicked(text)
                },
                isNothingClicked = isNothingClicked,
            )
        }
    }
}

@Composable
fun FoodCard(
    modifier: Modifier = Modifier,
    imageRes: Int,
    text: String,
    selected: Boolean,
    onCardClicked: (String) -> Unit,
    isNothingClicked: Boolean,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .clickable { onCardClicked(text) },
            contentAlignment = Alignment.Center
        ){
            //음식 카드인 경우
            if (imageRes != 0){
                Box(
                    modifier = Modifier
                        .alpha(alpha = if (isNothingClicked) 0.1f else 1f),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = text,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(RoundedCornerShape(6.dp)).fillMaxSize()
                    )
                    if (selected) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(6.dp))
                                .background(AconTheme.color.Dim_b_60)
                        )
                        Image(
                            imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_check_44),
                            contentDescription = "Clicked",
                            modifier = Modifier.size(44.dp)
                        )
                    }
                }
            } else { //없음 카드인 경우
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(6.dp))
                        .background(color = if (isNothingClicked) AconTheme.color.Dim_b_60 else AconTheme.color.Gray7),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        color = AconTheme.color.White,
                        style = AconTheme.typography.subtitle2_14_med
                    )
                    if(isNothingClicked){
                        Image(
                            imageVector = ImageVector.vectorResource(com.acon.core.designsystem.R.drawable.ic_check_44),
                            contentDescription = "Clicked",
                            modifier = Modifier.size(44.dp)
                        )
                    }
                }
            }
        }
        val cardTextAlpha = if (isNothingClicked && imageRes != 0) 0.1f else 1f
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.alpha(cardTextAlpha),
            text = text,
            color = AconTheme.color.White,
            style = AconTheme.typography.subtitle2_14_med
        )
    }
}

@Preview
@Composable
fun PreviewFoodGrid() {

    val selectedCard = remember { mutableStateOf(setOf<String>()) }

    Column(

    ){

        FoodGrid(
            columnSize = 3,
            foodItems = FoodItems.entries.toTypedArray(),
            onCardClicked = {
                if(selectedCard.value.contains(it)) selectedCard.value -= it
                else selectedCard.value += it
            },
            isNothingClicked = false,
            selectedCard = selectedCard.value
        )

//        FoodGrid(
//            columnSize = 3,
//            foodItems = FoodTypeItems.entries.toTypedArray(),
//            onCardClicked = {
//                if(selectedCard.value.contains(it)) selectedCard.value -= it
//                else selectedCard.value += it
//            },
//            isNothingClicked = false,
//            selectedCard = selectedCard.value
//        )
    }
}