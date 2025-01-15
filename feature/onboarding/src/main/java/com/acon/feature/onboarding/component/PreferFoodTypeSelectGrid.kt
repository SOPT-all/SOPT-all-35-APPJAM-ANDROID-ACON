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
import com.acon.feature.onboarding.type.FoodTypeItems

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
        contentPadding = PaddingValues(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ){
        items(foodItems) { food ->
            FoodTypeCard(
                imageRes = food.imageResId,
                text = food.cardName,
                selected = (selectedCard.contains(food.cardName)),
                onCardClicked = { text ->
                    onCardClicked(text)
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
    selected: Boolean,
    onCardClicked: (String) -> Unit,
    isAllClicked: Boolean, //이게 true가 되는 순간 (3개가 선택되는 순간), unselected인 애들은 전부 alpha 처리하기
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
                .clickable(enabled = selected || !isAllClicked) { onCardClicked(text) },
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
                        val rateIcon = if (selectedCard.indexOf(text) == 0) com.acon.feature.onboarding.R.drawable.ic_1
                                        else if (selectedCard.indexOf(text) == 1) com.acon.feature.onboarding.R.drawable.ic_2
                                        else if (selectedCard.indexOf(text) == 2) com.acon.feature.onboarding.R.drawable.ic_3
                                        else 0
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(6.dp))
                                .background(AconTheme.color.Dim_b_60)
                        )
                        Image(
                            imageVector = ImageVector.vectorResource(rateIcon),
                            contentDescription = "Clicked",
                            modifier = Modifier.size(44.dp)
                        )
//                        Text(
//                            text = "${selectedCard.indexOf(text) + 1}",
//                            style = AconTheme.typography.head8_16_sb,
//                            color = AconTheme.color.White,
//                            modifier = Modifier.align(Alignment.Center)
//                        )
                    }
                }

        }
        val cardTextAlpha = if (isAllClicked && !selected) 0.1f else 1f //수정 필요
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