package com.gautam.core

import android.app.Application
import androidx.databinding.ViewDataBinding

/**
 * Shared base view model
 *
 * @param SD
 * @param SE
 * @property sharedData
 * @constructor
 *
 * @param application
 *
 */// this type viewmodel is used when there is functionality sharing between the fragments / activity
abstract class SharedBaseViewModel<SD : BaseData, SE : BaseEvent>(
    application: Application,
    private val sharedData: SD
) : BaseViewModel<SD, SE>(application, sharedData) {

    /**
     * Bind shared data
     *
     * @param binding
     */// use this while in fragment to bind the activity/shared viewmodel data in fragment xmls
    internal fun bindSharedData(binding: ViewDataBinding) {
        binding.setVariable(BR.sharedData, sharedData)
    }

}