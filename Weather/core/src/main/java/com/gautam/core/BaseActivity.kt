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

abstract class BaseActivity<B : ViewDataBinding, E : BaseEvent, D : BaseData, out VM : BaseViewModel<D, E>>(
    viewModelClazz: KClass<VM>,
    @LayoutRes private val layoutId: Int
) : AppCompatActivity(), EventListener {

    private val viewModel: VM by viewModel(clazz = viewModelClazz)
    lateinit var binding: B
    lateinit var launchPeriodicAsync: Deferred<Any>

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


    override fun onStart() {
        super.onStart()
        launchPeriodicAsync = lifecycleScope.launchPeriodicAsync(2000, isConnected)
    }

    open fun getInitialData() {}

    override fun onStop() {
        super.onStop()
        if (launchPeriodicAsync.isActive)
            launchPeriodicAsync.cancel()
    }

    abstract override fun eventUpdated(event: BaseEvent)

    val isConnected: () -> Boolean = {
        var status = false

        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    status = true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    status = true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    status = true
                }
            }
        } else {
            status = connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
        status
    }

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