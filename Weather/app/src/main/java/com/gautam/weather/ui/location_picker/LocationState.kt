package com.gautam.weather.ui.location_picker


sealed class LocationState {
    object Loading : LocationState()
    object NoState : LocationState()
    data class Response(val result: List<String>, val searchTerm:String) : LocationState()
    data class Error(val error: String) : LocationState()
}