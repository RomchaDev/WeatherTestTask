package com.example.weathertesttask.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.weathertesttask.R
import com.example.weathertesttask.databinding.ActivityMainBinding
import com.example.weathertesttask.navigation.NavigationContainerActivity

class MainActivity : NavigationContainerActivity(
    R.id.fragment_container
) {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}