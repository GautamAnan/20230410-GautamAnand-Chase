package com.gautam.weather.ui.location_picker.list_logs

import androidx.recyclerview.widget.DiffUtil
import com.gautam.domain.model.WeatherModel


/**
 * Weather logs list diff callback
 *
 * @constructor Create empty Weather logs list diff callback
 */
class LocationLogsListDiffCallback : DiffUtil.ItemCallback<WeatherModel>() {
    override fun areItemsTheSame(
        oldItem: WeatherModel,
        newItem: WeatherModel
    ): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(
        oldItem: WeatherModel,
        newItem: WeatherModel
    ): Boolean =
        oldItem == newItem
}