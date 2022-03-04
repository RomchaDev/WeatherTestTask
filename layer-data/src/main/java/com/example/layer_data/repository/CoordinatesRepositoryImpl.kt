package com.example.layer_data.repository

import com.example.layer_data.data_sources.RemoteDataSource
import com.example.layer_domain.boundaries.CoordinatesRepository
import com.example.layer_domain.entity.coordinates.Coordinates

class CoordinatesRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : CoordinatesRepository {
    override suspend fun getCoordinates(city: String): Coordinates =
        remoteDataSource.getCoordinatesAnswer(city).await().coordinates
}