package com.gautam.core


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.gautam.core.BR
import com.gautam.core.fundamentals.EventListener
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

/**
 * Base activity
 *
 * @param B
 * @param E
 * @param D
 * @param VM
 * @property layoutId
 * @constructor
 *
 * @param viewModelClazz
 */
abstract class BaseActivity<B : ViewDataBinding, E : BaseEvent, D : BaseData, out VM : BaseViewModel<D, E>>(
    viewModelClazz: KClass<VM>,
    @LayoutRes private val layoutId: Int
) : AppCompatActivity(), EventListener {

    protected val viewModel: VM by viewModel(clazz = viewModelClazz)
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.apply {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.viewModel, this@BaseActivity.viewModel)
        }
        viewModel.bindData(binding)
        viewModel.liveEvents.observeForever {
            eventUpdated(it)
        }
        getInitialData()
    }


    /**
     * Get initial data
     *
     */
    open fun getInitialData() {}

    abstract override fun eventUpdated(event: BaseEvent)


    private fun CoroutineScope.launchPeriodicAsync(repeatMillis: Long, action: () -> Boolean) =
        this.async (Dispatchers.IO){
            if (repeatMillis > 0) {
                while (isActive) {
                    action()
                    delay(repeatMillis)
                }
            } else {
                return@async action()
            }
        }


}