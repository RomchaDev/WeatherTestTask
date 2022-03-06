package com.example.layer_domain.entity.weather

import java.util.*

data class Hour(
    private val dt: Long,
    val temp: Double,
    val weather: List<Weather>
) {
    val timeMillis get() = dt * 1000L

    fun getDate() = Date(timeMillis)
}