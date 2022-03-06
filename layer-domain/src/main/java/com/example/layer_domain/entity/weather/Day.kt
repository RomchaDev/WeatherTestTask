package com.example.layer_domain.entity.weather

import java.util.*

data class Day(
    private val dt: Long,
    val temp: Temp,
    val humidity: Int,
    val windSpeed: Double,
    val weather: List<Weather>
) {
    val dateMillis get() = dt * 1000L

    fun getDate() = Date(dateMillis)
}