package com.example.weathertesttask.app

import androidx.multidex.MultiDexApplication
import com.example.weathertesttask.koin.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                viewModelModule,
                navigationModule,
                mapperModule,
                useCaseModule,
                dataSourceModule,
                repositoryModule,
                retrofitModule
            )
        }
    }
}