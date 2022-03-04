package com.example.layer_domain.entity.weather

data class Hour(
    val temp: Float,
    val weathers: List<Weather>
)