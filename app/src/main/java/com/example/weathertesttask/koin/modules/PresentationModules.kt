package com.example.weathertesttask.koin.modules

import com.example.layer_presentation.core.navigation.AppNavigator
import com.example.weathertesttask.navigation.AndroidNavigator
import org.koin.dsl.module

val navigationModule = module {
    single { AndroidNavigator() }
    factory<AppNavigator> { get<AndroidNavigator>() }
}

val viewModelModule = module {

}