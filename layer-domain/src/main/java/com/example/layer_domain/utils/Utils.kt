package com.example.layer_domain.utils

import kotlin.math.roundToInt

fun Double.toCelsius(): String {
    val c = (this - 273).roundToInt()
    return "$c°"
}