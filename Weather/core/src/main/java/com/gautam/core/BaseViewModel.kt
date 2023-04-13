package com.gautam.core

import android.app.Application
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.AndroidViewModel
import com.gautam.core.fundamentals.SingleLiveEvent
import org.koin.core.component.KoinComponent

abstract class BaseViewModel<D : BaseData, E : BaseEvent>(
    application: Application,
    val data: D
) : AndroidViewModel(application), KoinComponent {
    val liveEvents: SingleLiveEvent<E> = SingleLiveEvent()


    internal fun bindData(binding: ViewDataBinding) {
        binding.setVariable(BR.data, data)
    }

    infix fun updateEvent(event: E?) {
        event?.let { liveEvents.postValue(event) }
    }



}