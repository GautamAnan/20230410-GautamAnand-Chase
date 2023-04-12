package com.gautam.core

import androidx.lifecycle.MutableLiveData

/**
 * Base data
 *
 * @property showLoading
 * @property showError
 * @property networkStatus
 * @constructor Create empty Base data
 */
abstract class BaseData(
    var showLoading: MutableLiveData<Boolean> = MutableLiveData(false),
    var showError: MutableLiveData<Boolean> = MutableLiveData(false),
) {
    /**
     * Loading
     *
     */
    fun loading() {
        showLoading.postValue(true)
        showError.postValue(false)
    }

    /**
     * Hide loading
     *
     */
    fun hideLoading() {
        showLoading.postValue(false)
        showError.postValue(false)
    }

    /**
     * Error
     *
     */
    fun error() {
        showLoading.postValue(false)
        showError.postValue(true)
    }

}