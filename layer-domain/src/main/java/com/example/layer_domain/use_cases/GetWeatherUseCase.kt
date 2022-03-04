package com.example.layer_domain.use_cases

import com.example.layer_domain.boundaries.CoordinatesRepository
import com.example.layer_domain.boundaries.WeatherRepository
import com.example.layer_domain.entity.weather.WeatherEntity
import com.example.layer_domain.use_cases.arguments.WeatherUseCaseArgument
import com.example.layer_domain.use_cases.exceptions.UseCaseNullArgException
import java.lang.NullPointerException

// It would be a good idea to divide this UseCase to 3 different ones (GetDailyUseCase,
// GetHourlyUseCase and GetCurrentUseCase) if the api didn't send all this info in one
// response together.
class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    private val coordinatesRepository: CoordinatesRepository
) : UseCase<WeatherEntity, WeatherUseCaseArgument> {

    // Null assertion has to be used here, because all exceptions that extend IOException
    // should are caught in view model
    override suspend fun execute(arg: WeatherUseCaseArgument?): WeatherEntity {
        try {
            arg!!.let {
                val coordinates = coordinatesRepository.getCoordinates(arg.city)
                return weatherRepository.getWeatherAnswer(coordinates.lat, coordinates.lon)
            }

        } catch (e: NullPointerException) {
            throw UseCaseNullArgException() // Only IOException's children can be caught in VM
        }
    }
}