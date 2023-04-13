package com.gautam.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gautam.core.fundamentals.EventListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

/**
 * Base fragment
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
abstract class BaseFragment<B : ViewDataBinding, E : BaseEvent, D : BaseData, VM : BaseViewModel<D, E>>(
    viewModelClazz: KClass<VM>,
    @LayoutRes private val layoutId: Int
) : Fragment(), EventListener {
    val viewModel: VM by viewModel(clazz = viewModelClazz)
    var binding: B? = null
    private var eventListener: EventListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false)
        binding?.apply {
            lifecycleOwner = this@BaseFragment
            setVariable(BR.viewModel, this@BaseFragment.viewModel)
            viewModel.bindData(this)
        }
        getInitialData()
        viewModel.liveEvents.observe(viewLifecycleOwner, Observer {
            eventListener?.eventUpdated(it)
        })
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
    }

    abstract override fun eventUpdated(event: BaseEvent)
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    /**
     * Get initial data
     *
     */
    open fun getInitialData() {}

    /**
     * Setup view
     *
     * @param view
     */
    protected abstract fun setupView(view: View)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        eventListener = this@BaseFragment
    }

    val isConnected: () -> Boolean = {
        var status = false
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
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
        status
    }
}