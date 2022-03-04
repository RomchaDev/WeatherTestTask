package com.example.layer_domain.boundaries

import com.example.layer_domain.entity.weather.WeatherEntity

interface WeatherRepository {
    suspend fun getWeatherAnswer(lat: Double, lon: Double): WeatherEntity
}