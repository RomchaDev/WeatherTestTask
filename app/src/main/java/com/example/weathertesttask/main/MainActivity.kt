package com.example.weathertesttask.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.layer_data.location.LocationWorker
import com.example.weathertesttask.R
import com.example.weathertesttask.databinding.ActivityMainBinding
import com.example.weathertesttask.location.AndroidLocationWorker
import com.example.weathertesttask.navigation.NavigationContainerActivity
import org.koin.android.ext.android.inject

class MainActivity : NavigationContainerActivity(
    R.id.fragment_container
) {
    private var binding: ActivityMainBinding? = null
    private val locationWorker: AndroidLocationWorker by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        locationWorker.locationActivity = this


        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}