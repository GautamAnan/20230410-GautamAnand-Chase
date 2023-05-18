package com.gautam.weather.ui.view_weather

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gautam.core.utils.ImagesCache


class InformationPageViewModel : ViewModel() {
    var imageOffline: MutableLiveData<Bitmap?> = MutableLiveData()

    fun checkImageIfAvailable(iconName: String?) {
        imageOffline.postValue(ImagesCache.getImageFromWarehouse(iconName))
    }

}