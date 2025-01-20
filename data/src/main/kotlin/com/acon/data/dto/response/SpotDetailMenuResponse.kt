package com.acon.data.dto.response

import com.acon.domain.model.spot.SpotDetailInfo
import com.acon.domain.model.spot.SpotDetailMenu
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotDetailMenuListResponse(
    @SerialName("spotMenuList") val spotMenuList: List<SpotDetailMenuResponse>
)

@Serializable
data class SpotDetailMenuResponse(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("price") val price: Int,
    @SerialName("image") val image: String?,
) {
    fun toSpotDetailMenu() = SpotDetailMenu(
        id = id,
        name =  name,
        price = price,
        image = image
    )
}
