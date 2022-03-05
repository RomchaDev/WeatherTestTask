package com.example.layer_domain.entity.weather

import com.example.layer_domain.utils.toCelsius

data class Temp(
    val eve: Double,
    val min: Double,
    val max: Double
) {
    override fun toString(): String {
        return "${max.toCelsius()}/${min.toCelsius()}"
    }
}