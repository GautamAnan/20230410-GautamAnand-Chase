package com.gautam.core

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


abstract class BaseListAdapter<T, VH : BaseViewHolder<T>>(diffCallback: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, VH>(diffCallback) {

    var listItemClickListener: (model: T, viewId: Int, clickedPosition: Int) -> Unit = { _, _, _ -> }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position), position)

    fun setOnListItemClickListener(onListItemClickListener: (model: T, viewId: Int, clickedPosition: Int) -> Unit) {
        this.listItemClickListener = onListItemClickListener
    }
}

open class BaseViewHolder<T>(
    private val binding: ViewDataBinding,
    private val listener: ((model: T, viewId: Int, clickedPosition: Int) -> Unit)? = null,
    private val viewClickEvents: Array<View> = emptyArray()
) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(model: T, selectedPosition: Int) {
        binding.setVariable(BR.data, model)
        binding.executePendingBindings()
        viewClickEvents.forEach { it ->
            it.setOnClickListener { this.listener?.invoke(model, it.id, selectedPosition) }
        }
    }
}