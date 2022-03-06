package com.example.layer_domain.use_cases

import com.example.layer_domain.boundaries.CoordinatesRepository
import com.example.layer_domain.boundaries.WeatherRepository
import com.example.layer_domain.entity.weather.WeatherEntity

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    private val coordinatesRepository: CoordinatesRepository
) : UseCase<WeatherEntity> {

    suspend fun execute(city: String): WeatherEntity {
        val coordinates = coordinatesRepository.getCoordinates(city)
        return weatherRepository.getWeatherAnswer(coordinates.lat, coordinates.lon)
    }
}