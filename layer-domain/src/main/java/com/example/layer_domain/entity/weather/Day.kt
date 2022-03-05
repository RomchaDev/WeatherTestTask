package com.example.layer_domain.entity.weather

data class Day(
    val temp: Temp,
    val humidity: Int,
    val windSpeed: Double,
    val weather: List<Weather>
)