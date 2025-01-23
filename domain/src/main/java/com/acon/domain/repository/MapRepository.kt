package com.acon.domain.repository

interface MapRepository {

    suspend fun fetchLegalAddressName(
        latitude: Double,
        longitude: Double
    ): Result<String>
}