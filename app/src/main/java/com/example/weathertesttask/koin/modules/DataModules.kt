package com.example.weathertesttask.koin.modules

import com.example.layer_data.api.ApiService
import com.example.layer_data.api.MAIN_API_URL
import com.example.layer_data.api.MainInterceptor
import com.example.layer_data.data_sources.RemoteDataSource
import com.example.layer_data.data_sources.RemoteDataSourceImpl
import com.example.layer_data.location.LocationWorker
import com.example.layer_data.repository.CoordinatesRepositoryImpl
import com.example.layer_data.repository.WeatherRepositoryImpl
import com.example.layer_data.time.MainTimeWorker
import com.example.layer_data.time.TimeWorker
import com.example.layer_domain.boundaries.CoordinatesRepository
import com.example.layer_domain.boundaries.WeatherRepository
import com.example.weathertesttask.location.AndroidLocationWorker
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single {

        val client = OkHttpClient.Builder()
            .addInterceptor(MainInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

        Retrofit.Builder()
            .baseUrl(MAIN_API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client)
            .build().create(ApiService::class.java)
    }
}

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
}

val repositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
    single<CoordinatesRepository> { CoordinatesRepositoryImpl(get()) }
}

val timeModule = module {
    single<TimeWorker> { MainTimeWorker() }
}

val locationModule = module {
    single { AndroidLocationWorker() }
    factory<LocationWorker> { get<AndroidLocationWorker>() }
}