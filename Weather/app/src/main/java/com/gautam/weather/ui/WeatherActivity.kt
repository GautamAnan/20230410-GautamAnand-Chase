package com.gautam.weather.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gautam.weather.theme.WeatherAppTheme
import com.gautam.weather.ui.view_weather.InformationPage
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                val navController = rememberNavController()
                val viewModel: WeatherViewModel by viewModel()

                NavHost(navController = navController, startDestination = "Information") {
                    composable("Information") {
                        InformationPage(navController= navController, sharedViewModel = viewModel)
                    }
                    composable("view/{userName}") {
                      //  Screen2(it.arguments?.getString("userName"))
                    }
                }

            }
        }
    }

    /*  override fun eventUpdated(event: BaseEvent) {
         when(event){


             is WeatherEvents.NetworkError -> {
                 this.displayErrorDialog("No network connection. Please try again")
             }

             is WeatherEvents.CallError -> {
                 this.displayErrorDialog(event.errorMsg)
             }
         }
      }


  */
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    WeatherAppTheme {
       // InformationPage()
    }
}