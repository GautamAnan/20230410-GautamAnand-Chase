package com.gautam.core.fundamentals

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Single live event
 *
 * @param T
 * @constructor Create empty Single live event
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    /**
     * Call
     *
     */
    @MainThread
    fun call() {
        value = null
    }

    /**
     * Invoke
     *
     */
    operator fun invoke() {
        call()
    }

    /**
     * Invoke
     *
     * @param data
     */
    operator fun invoke(data: T) {
        value = data
    }

}