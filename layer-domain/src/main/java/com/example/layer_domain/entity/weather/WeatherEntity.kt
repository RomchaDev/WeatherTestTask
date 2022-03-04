package com.example.layer_domain.entity.weather

class WeatherEntity(
    val daily: List<Day>,
    val hourly: List<Hour>,
    val current: Current
)