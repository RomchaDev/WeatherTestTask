package com.example.weathertesttask.koin.modules

import com.example.layer_presentation.core.navigation.AppNavigator
import com.example.layer_presentation.core.sky_state.AndroidIconFromIdGetter
import com.example.layer_presentation.core.sky_state.IconFromIdGetter
import com.example.layer_presentation.main.home.HomeViewModel
import com.example.weathertesttask.navigation.AndroidNavigator
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val navigationModule = module {
    single { AndroidNavigator() }
    factory<AppNavigator> { get<AndroidNavigator>() }
}

val iconModule = module {
    single<IconFromIdGetter> { AndroidIconFromIdGetter() }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get()) }
}