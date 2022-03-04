package com.example.layer_data.data_sources

import com.example.layer_data.dto.coordinates.CoordinatesAnswer
import com.example.layer_data.dto.weather.WeatherAnswer
import kotlinx.coroutines.Deferred

interface RemoteDataSource {
    fun getWeatherAnswer(
        lat: Double,
        lon: Double
    ): Deferred<WeatherAnswer>

    fun getCoordinatesAnswer(
        city: String
    ): Deferred<CoordinatesAnswer>
}