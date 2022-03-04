package com.example.layer_domain.entity.weather

data class Current (
    val windSpeed: Float,
    val temp: Float,
    private val weather: List<Weather>
)