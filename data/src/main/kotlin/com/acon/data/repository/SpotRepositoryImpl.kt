package com.acon.data.repository

import com.acon.data.datasource.remote.SpotRemoteDataSource
import com.acon.data.dto.request.ConditionRequest
import com.acon.data.dto.request.FilterListRequest
import com.acon.data.dto.request.SpotListRequest
import com.acon.data.error.runCatchingWith
import com.acon.domain.error.spot.FetchSpotListError
import com.acon.domain.model.spot.Condition
import com.acon.domain.model.spot.Spot
import com.acon.domain.repository.SpotRepository
import javax.inject.Inject

class SpotRepositoryImpl @Inject constructor(
    private val spotRemoteDataSource: SpotRemoteDataSource
) : SpotRepository {

    override suspend fun fetchSpotList(
        latitude: Double,
        longitude: Double,
        condition: Condition,
        walkingTime: Int,
        priceRange: Int
    ): Result<List<Spot>> {
        return runCatchingWith(*FetchSpotListError.createErrorInstances()) {
            spotRemoteDataSource.fetchSpotList(
                SpotListRequest(
                    latitude = latitude,
                    longitude = longitude,
                    condition = ConditionRequest(
                        spotType = condition.spotType.name,
                        filterList = condition.filterList.map { filter ->
                            FilterListRequest(
                                category = filter.category.name,
                                optionList = filter.optionList.map { optionTypes -> optionTypes.getName() }
                            )
                        }
                    ),
                    walkingTime = walkingTime,
                    priceRange = priceRange
                )
            ).spotList.map { it.toSpot() }
        }
    }
}