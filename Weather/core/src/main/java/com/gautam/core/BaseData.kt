package com.gautam.core

import androidx.lifecycle.MutableLiveData

abstract class BaseData(
    var showLoading: MutableLiveData<Boolean> = MutableLiveData(false),
    var showError: MutableLiveData<Boolean> = MutableLiveData(false),
    var networkStatus: MutableLiveData<Boolean> = MutableLiveData(true)
) {
    fun loading() {
        showLoading.postValue(true)
        showError.postValue(false)
    }

    fun hideLoading() {
        showLoading.postValue(false)
        showError.postValue(false)
    }

    fun error() {
        showLoading.postValue(false)
        showError.postValue(true)
    }

}