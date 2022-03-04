package com.example.layer_data.data_sources

import com.example.layer_data.api.ApiService

class RemoteDataSourceImpl(
    private val apiService: ApiService
) : RemoteDataSource {

    override fun getWeatherAnswer(lat: Double, lon: Double) =
        apiService.getWeatherAnswer(lat, lon)

    override fun getCoordinatesAnswer(city: String) =
        apiService.getCoordinatesAnswer(city)
}