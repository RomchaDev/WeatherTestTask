package com.example.layer_presentation.core.sky_state

import com.example.layer_presentation.R

class AndroidIconFromIdGetter : IconFromIdGetter {

    private val default = R.drawable.ic_sunny

    private val skyMap = mapOf(
        listOf(200..232) to R.drawable.ic_thunder,
        listOf(300..321, 500..531) to R.drawable.ic_rain,
        listOf(600..622, 700..781) to R.drawable.ic_snow,
        listOf(800..800) to R.drawable.ic_sunny,
        listOf(801..801) to R.drawable.ic_cloudy_1,
        listOf(802..804) to R.drawable.ic_cloudy_2
    )

    override fun get(id: Int): Int {
        val keys = skyMap.keys

        val key = keys.find { ranges ->
            ranges.forEach { range ->
                if (id in range) return@find true
            }

            return@find false
        }

        return skyMap[key] ?: default
    }
}