/*
package com.gautam.weather.ui.location_picker.list_logs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gautam.core.BaseListAdapter
import com.gautam.core.BaseViewHolder
import com.gautam.domain.model.WeatherModel
import com.gautam.weather.databinding.LayoutMainpageLogsBinding


*/
/**
 * Weather logs list adapter
 *
 * @constructor Create empty Weather logs list adapter
 *//*

class LocationLogsListAdapter :
    BaseListAdapter<String, LocationLogsListAdapter.WeatherLogsListViewHolder>(
        LocationLogsListDiffCallback()
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherLogsListViewHolder {
        val layoutItemBinding = LayoutMainpageLogsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WeatherLogsListViewHolder(
            binding = layoutItemBinding,
            itemClickListener = this.listItemClickListener,
            arrayViewClickable = arrayOf(
                layoutItemBinding.tvHead
            )
        )
    }

    */
/**
     * Weather logs list view holder
     *
     * @property binding
     * @constructor
     *
     * @param itemClickListener
     * @param arrayViewClickable
     *//*

    class WeatherLogsListViewHolder(
        val binding: LayoutMainpageLogsBinding,
        itemClickListener: (String, Int, Int) -> Unit,
        arrayViewClickable: Array<View>
    ) : BaseViewHolder<String>(binding, itemClickListener, arrayViewClickable)
}*/
