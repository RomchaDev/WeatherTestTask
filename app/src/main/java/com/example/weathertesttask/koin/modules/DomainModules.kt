package com.example.weathertesttask.koin.modules

import com.example.layer_data.dto.weather.WeatherAnswer
import com.example.layer_data.mapping.Mapper
import com.example.layer_data.mapping.WeatherMapper
import com.example.layer_domain.entity.weather.WeatherEntity
import com.example.layer_domain.use_cases.GetWeatherUseCase
import com.example.layer_domain.use_cases.UseCase
import com.example.layer_domain.use_cases.arguments.WeatherUseCaseArgument
import org.koin.dsl.module

val mapperModule = module {
    single<Mapper<WeatherAnswer, WeatherEntity>> { WeatherMapper() }
}

val useCaseModule = module {
    single<UseCase<WeatherEntity, WeatherUseCaseArgument>> { GetWeatherUseCase(get(), get()) }
}