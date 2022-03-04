package com.example.layer_domain.entity.weather

data class Day(
    val temp: Temp,
    val weathers: List<Weather>
)