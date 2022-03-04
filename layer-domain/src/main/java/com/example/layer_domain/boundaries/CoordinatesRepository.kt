package com.example.layer_domain.boundaries

import com.example.layer_domain.entity.coordinates.Coordinates

interface CoordinatesRepository {
    suspend fun getCoordinates(city: String): Coordinates
}