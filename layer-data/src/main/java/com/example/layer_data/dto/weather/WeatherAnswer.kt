package com.example.layer_data.dto.weather

import com.example.layer_domain.entity.weather.Current
import com.example.layer_domain.entity.weather.Day
import com.example.layer_domain.entity.weather.Hour

data class WeatherAnswer(
    val daily: List<Day>,
    val hourly: List<Hour>,
    val current: Current
)