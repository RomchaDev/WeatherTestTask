package com.example.weathertesttask.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import com.example.layer_data.location.LocationWorker
import java.util.*

class AndroidLocationWorker : LocationWorker {

    // Should be set by MainActivity
    var locationActivity: AppCompatActivity? = null

    override fun getCityName(): String? {
        return if (locationActivity?.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            val locationManager =
                locationActivity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

            val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

            location?.let { getCityByCoordinates(location.latitude, location.longitude) }

        } else {
            requestLocationPermission()
            null
        }
    }

    private fun requestLocationPermission() {
        locationActivity?.requestPermissions(
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            1000
        )
    }

    private fun getCityByCoordinates(lat: Double, lon: Double): String? {
        val geocoder = Geocoder(locationActivity, Locale.getDefault())

        val addresses = geocoder.getFromLocation(lat, lon, 10)
        addresses.forEach { ad ->
            ad?.let {
                if (ad.locality.isNotEmpty()) return ad.locality
            }
        }

        return null
    }
}