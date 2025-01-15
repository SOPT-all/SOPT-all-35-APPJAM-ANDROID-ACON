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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.acon.feature.onboarding.R

data class FoodItem(val imageRes: Int, val name: String, val isSelected: Boolean)

@Composable
fun FoodGrid(
    modifier : Modifier = Modifier,
    columnSize : Int,
    totalItemsCount : Int,
    foodItemList: List<FoodItem>,
    onCardClicked: (String) -> Unit,
    isNothingClicked: Boolean,
    selectedCard: Set<String>,
){

    val rowsCount = ( (totalItemsCount - 1) / columnSize ) + 1

    LazyVerticalGrid(
        columns = GridCells.Fixed(columnSize),
        contentPadding = PaddingValues(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    ){
        items(foodItemList) { food ->
            FoodCard(
                imageRes = food.imageRes,
                text = food.name,
                selected = (selectedCard.contains(food.name)),
                onCardClicked = { text ->
                    onCardClicked(text)
                },
                isNothingClicked = isNothingClicked,
                selectedCard = selectedCard
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
                .clickable { onCardClicked(text) },
            contentAlignment = Alignment.Center
        ){
            //음식 카드인 경우
            if (imageRes != 0){
                Box(
                    modifier = Modifier
                        //.background(color = if (selectedCard.contains(text)) Color(0x3C000000) else Color(0x00000000))
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
                                .background(color = Color(0x3C000000))
                        )
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Clicked",
                            tint = Color.LightGray,
                            modifier = Modifier
                                .size(44.dp)
                        )
                    }
                }
            } else { //없음 카드인 경우
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(6.dp))
                        .background(color = if (isNothingClicked) Color(0x3C000000) else Color(0xFF323339)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                    if(isNothingClicked){
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Clicked",
                            tint = Color(0xFFFFFFFF),
                            modifier = Modifier
                                .size(44.dp)
                        )
                    }
                }
            }
        }
        val cardTextAlpha = if (isNothingClicked && imageRes != 0) 0.1f else 1f
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = modifier.alpha(cardTextAlpha),
            text = text,
            fontSize = 14.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun PreviewFoodGrid() {

    val foodItems = listOf(
        FoodItem(R.drawable.food_img_1, "닭발", false),
        FoodItem(R.drawable.food_img_2, "회/육회", false),
        FoodItem(R.drawable.food_img_3, "곱창/대창/막창", false),
        FoodItem(R.drawable.food_img_4, "순대/선지", false),
        FoodItem(R.drawable.food_img_5, "양고기", false),
        FoodItem(0, "없음", false)
    )

    val selectedCard = remember { mutableStateOf(setOf<String>()) }

    FoodGrid(
        columnSize = 3,
        totalItemsCount = 5,
        foodItemList = foodItems,
        onCardClicked = {
            if(selectedCard.value.contains(it)) selectedCard.value -= it
            else selectedCard.value += it
        },
        isNothingClicked = false,
        selectedCard = selectedCard.value
    )
}