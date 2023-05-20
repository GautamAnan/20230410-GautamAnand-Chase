package com.gautam.weather.ui.view_weather

import android.Manifest
import android.annotation.SuppressLint
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import java.util.concurrent.TimeUnit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.PermissionsRequired
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.LatLng

//A callback for receiving notifications from the FusedLocationProviderClient.
private lateinit var locationCallback: LocationCallback

//The main entry point for interacting with the Fused Location Provider
@SuppressLint("StaticFieldLeak")
lateinit var locationProvider: FusedLocationProviderClient

private const val LOCATION_TAG = "LOCATION_TAG"

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AccompanistPermissionsScreen(sharedViewModel: InformationPageViewModel) {
    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    if (!locationPermissionState.allPermissionsGranted) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LocationPermissions(multiplePermissionState = locationPermissionState, sharedViewModel)
            Button(
                onClick = {
                    locationPermissionState.launchMultiplePermissionRequest()
                }
            ) {
                Text(text = "Use location")
            }
        }
    } else
        GetUserLocation(sharedViewModel)
}

@SuppressLint("MissingPermission")
@Composable
private fun GetUserLocation(sharedViewModel: InformationPageViewModel) {

    val context = LocalContext.current

    // The Fused Location Provider provides access to location APIs.
    locationProvider = LocationServices.getFusedLocationProviderClient(context)

    DisposableEffect(key1 = locationProvider) {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                /**
                 * This returns the most recent historical location currently available.
                 * Will return null if no historical location is available
                 * */
                locationProvider.lastLocation
                    .addOnSuccessListener { location ->
                        location?.let {
                            val lat = location.latitude
                            val long = location.longitude
                            sharedViewModel.getWeatherByLocation(LatLng(lat, long))
                        }
                    }
                    .addOnFailureListener {
                        Log.e("Location_error", "${it.message}")
                    }

            }
        }

        locationUpdate()

        // when the effects leaves the composition, stop location updates
        onDispose {
            stopLocationUpdate()

        }
    }

}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun LocationPermissions(
    multiplePermissionState: MultiplePermissionsState,
    sharedViewModel: InformationPageViewModel
) {
    PermissionsRequired(
        multiplePermissionsState = multiplePermissionState,
        permissionsNotGrantedContent = { /* ... */ },
        permissionsNotAvailableContent = { /* ... */ }
    ) {
        GetUserLocation(sharedViewModel = sharedViewModel)
    }
}

private fun stopLocationUpdate() {
    try {
        //Removes all location updates for the given callback.
        val removeTask = locationProvider.removeLocationUpdates(locationCallback)
        removeTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d(LOCATION_TAG, "Location Callback removed.")
            } else {
                Log.d(LOCATION_TAG, "Failed to remove Location Callback.")
            }
        }
    } catch (se: SecurityException) {
        Log.e(LOCATION_TAG, "Failed to remove Location Callback.. $se")
    }
}

@SuppressLint("MissingPermission")
private fun locationUpdate() {
    locationCallback.let {
        //An encapsulation of various parameters for requesting
        // location through FusedLocationProviderClient.
        val locationRequest: LocationRequest =
            LocationRequest.create().apply {
                interval = TimeUnit.SECONDS.toMillis(60)
                fastestInterval = TimeUnit.SECONDS.toMillis(30)
                maxWaitTime = TimeUnit.MINUTES.toMillis(2)
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            }
        //use FusedLocationProviderClient to request location update
        locationProvider.requestLocationUpdates(
            locationRequest,
            it,
            Looper.getMainLooper()
        )
    }

}
