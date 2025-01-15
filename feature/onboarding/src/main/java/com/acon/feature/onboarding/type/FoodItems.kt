package com.acon.feature.onboarding.type

import androidx.annotation.DrawableRes
import com.acon.feature.onboarding.R

interface CardItem {
    val imageResId: Int
    val cardName: String
}

enum class FoodItems(
    @DrawableRes override val imageResId: Int,
    override val cardName: String,
) : CardItem {

    DAKBAL(R.drawable.food_img_1, "닭발"),
    RAW(R.drawable.food_img_2, "회/육회"),
    INTESTINES(R.drawable.food_img_3, "곱창/대창/막창"),
    SUNDAE(R.drawable.food_img_4, "순대/선지"),
    SHEEP(R.drawable.food_img_5, "양고기"),
    NONE(0, "없음")
}

enum class FoodTypeItems(
    @DrawableRes override val imageResId: Int,
    override val cardName: String,
) : CardItem {
    KOREAN(R.drawable.food_korean, "한식"),
    AMERICAN(R.drawable.food_american, "양식"),
    CHINESE(R.drawable.food_chinese, "중식"),
    JAPANESE(R.drawable.food_japanese, "일식"),
    STREET(R.drawable.food_street, "분식"),
    ASIAN(R.drawable.food_asian, "아시안"),
}
