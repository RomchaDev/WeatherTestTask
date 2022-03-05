package com.example.layer_domain.entity.weather

data class WeatherEntity(
    val daily: List<Day>,
    val hourly: List<Hour>
)