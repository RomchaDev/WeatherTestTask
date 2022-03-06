package com.example.layer_data.repository

import com.example.layer_data.data_sources.RemoteDataSource
import com.example.layer_data.dto.weather.WeatherAnswer
import com.example.layer_data.mapping.Mapper
import com.example.layer_data.mapping.WeatherMapper
import com.example.layer_domain.boundaries.WeatherRepository
import com.example.layer_domain.entity.weather.WeatherEntity

class WeatherRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val weatherMapper: Mapper<WeatherAnswer, WeatherEntity>
) : WeatherRepository {

    override suspend fun getWeatherAnswer(lat: Double, lon: Double): WeatherEntity {
        val answer = remoteDataSource.getWeatherAnswer(lat, lon).await()
        return weatherMapper.map(answer)
    }
}