package com.gautam.weather.ui.location_picker.list_logs

import androidx.recyclerview.widget.DiffUtil
import com.gautam.domain.model.WeatherModel


/**
 * Weather logs list diff callback
 *
 * @constructor Create empty Weather logs list diff callback
 */
class LocationLogsListDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean =
        oldItem === newItem

    override fun areContentsTheSame(
        oldItem: String,
        newItem: String
    ): Boolean =
        oldItem == newItem
}