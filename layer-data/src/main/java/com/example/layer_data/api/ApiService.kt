package com.example.layer_data.api

import com.example.layer_data.dto.coordinates.CoordinatesAnswer
import com.example.layer_data.dto.weather.WeatherAnswer
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/onecall")
    fun getUniversalAnswer(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double
    ): Deferred<WeatherAnswer>

    @GET("data/2.5/weather")
    fun getCoordinatesAnswer(
        @Query("q") city: String
    ): Deferred<CoordinatesAnswer>
}