package com.gautam.weather.ui

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.SimpleItemAnimator
import com.gautam.core.BaseActivity
import com.gautam.core.BaseEvent
import com.gautam.weather.R
import com.gautam.weather.databinding.ActivityWeatherBinding
import com.gautam.weather.ui.location_picker.list_logs.LocationLogsListAdapter

class WeatherActivity :
    BaseActivity<ActivityWeatherBinding, WeatherEvents, WeatherData, WeatherViewModel>(
        WeatherViewModel::class,
        R.layout.activity_weather
    ) {
    private lateinit var navController: NavController
    private lateinit var navigationHost: NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationHost =
            supportFragmentManager.findFragmentById(R.id.fcvContainer) as NavHostFragment
        navController = navigationHost.navController

    }
    override fun eventUpdated(event: BaseEvent) {
       when(event){
           is WeatherEvents.ChangeLocation->{
               navController.navigate(R.id.locationFragment)
           }
           is WeatherEvents.CallHomeScreen -> {
               finishCurrentFragment()
           }
       }
    }

    private fun finishCurrentFragment() {
        if (!navController.popBackStack()) {
            finish()
        }
    }

}