package com.gautam.weather.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gautam.weather.theme.WeatherAppTheme
import com.gautam.weather.ui.location_picker.SearchLocation
import com.gautam.weather.ui.view_weather.InformationPage

class WeatherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "Information/{searchText}") {
                    composable("Information/{searchText}") {backStackEntry->
                        val searchText = backStackEntry.arguments?.getString("searchText")
                        InformationPage(navController = navController,searchText)
                    }
                    composable("search") {
                        SearchLocation(navController = navController)
                    }
                }

            }
        }
    }
}


