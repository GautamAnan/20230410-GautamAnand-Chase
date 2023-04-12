package com.gautam.weather.ui.view_weather

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.res.ResourcesCompat
import com.gautam.core.BaseEvent

import com.gautam.core.SharedBaseFragment
import com.gautam.core.fundamentals.displayErrorDialog
import com.gautam.weather.R
import com.gautam.weather.databinding.FragmentWeatherInfoBinding
import com.gautam.weather.ui.WeatherData
import com.gautam.weather.ui.WeatherEvents
import com.gautam.weather.ui.WeatherViewModel
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng


class InformationPageFragment :
    SharedBaseFragment<FragmentWeatherInfoBinding, InformationPageEvent, InformationPageData, InformationPageViewModel, WeatherEvents, WeatherData, WeatherViewModel>(
        WeatherViewModel::class,
        InformationPageViewModel::class,
        R.layout.fragment_weather_info
    ) {
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    override fun eventUpdated(event: BaseEvent) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient =
            this.let { LocationServices.getFusedLocationProviderClient(requireActivity()) }
        locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 3 * 1000
        locationRequest.fastestInterval = (1 * 1000).toLong()

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        } else {
            runTheLocationService()
        }

    }

    override fun setupView(view: View) {

    }

    @SuppressLint("MissingPermission")
    private fun runTheLocationService() {
        fusedLocationClient?.lastLocation
            ?.addOnSuccessListener { it ->
                if (it != null) {
                    sharedViewModel.getWeatherByLocation(LatLng(it.latitude, it.longitude))
                } else {
                    locationCallback = object : LocationCallback() {
                        override fun onLocationResult(locationResult: LocationResult) {
                            locationResult.locations.let {
                                sharedViewModel.getWeatherByLocation(
                                    LatLng(
                                        it[0].latitude,
                                        it[0].longitude
                                    )
                                )
                                if (fusedLocationClient != null) {
                                    fusedLocationClient!!.removeLocationUpdates(locationCallback)
                                }
                            }
                        }
                    }
                    fusedLocationClient?.requestLocationUpdates(
                        locationRequest,
                        locationCallback,
                        null
                    )
                }
            }

    }


    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
                runTheLocationService()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
                runTheLocationService()
            }
            else -> {
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
                Toast.makeText(
                    requireContext(),
                    "location permission denied",
                    Toast.LENGTH_LONG
                )
                    .show()
                activity?.finish()
            }
        }

    }


}

