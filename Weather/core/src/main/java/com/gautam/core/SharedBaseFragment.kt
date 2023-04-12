package com.gautam.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.reflect.KClass

/**
 * Shared base fragment
 *
 * @param B
 * @param ME
 * @param MD
 * @param MVM
 * @param SE
 * @param SD
 * @param SVM
 * @property layoutId
 * @constructor
 *
 * @param sharedViewModelClazz
 * @param mainViewModelClazz
 *
 *///  use this while having fragment where there is need to have a shared viewmodel and thier own viewmodel
abstract class SharedBaseFragment<B : ViewDataBinding, ME : BaseEvent, MD : BaseData, MVM : BaseViewModel<MD, ME>, SE : BaseEvent, SD : BaseData, SVM : SharedBaseViewModel<SD, SE>>(
    sharedViewModelClazz: KClass<SVM>,
    mainViewModelClazz: KClass<MVM>,
    @LayoutRes private val layoutId: Int
) : BaseFragment<B, ME, MD, MVM>(mainViewModelClazz, layoutId) {

    protected val sharedViewModel: SVM by sharedViewModel(clazz = sharedViewModelClazz)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding?.apply {
            setVariable(BR.sharedViewModel, this@SharedBaseFragment.sharedViewModel)
            sharedViewModel.bindSharedData(this)
        }
        return binding?.root
    }

}