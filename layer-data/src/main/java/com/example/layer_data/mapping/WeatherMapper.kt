package com.example.layer_data.mapping

import com.example.layer_data.dto.weather.WeatherAnswer
import com.example.layer_domain.entity.weather.WeatherEntity

class WeatherMapper : Mapper<WeatherAnswer, WeatherEntity> {
    override fun map(from: WeatherAnswer) = with(from) {
        WeatherEntity(
            daily = daily,
            hourly = hourly
        )
    }
}