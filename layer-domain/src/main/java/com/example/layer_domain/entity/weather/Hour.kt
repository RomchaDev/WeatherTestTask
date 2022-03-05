package com.example.layer_domain.entity.weather

data class Hour(
    val temp: Double,
    val weather: List<Weather>
)