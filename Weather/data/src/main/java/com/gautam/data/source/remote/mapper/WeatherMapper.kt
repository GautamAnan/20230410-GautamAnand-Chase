package com.gautam.data.source.remote.mapper

import com.gautam.core.utils.DateUtils
import com.gautam.data.entity.CurrentWeather
import com.gautam.domain.model.*


/**
 * Weather mapper
 *
 * @constructor Create empty Weather mapper
 */
class WeatherMapper {

    /**
     * To current weather model
     *
     * @param entity
     * @return
     */
    fun toCurrentWeatherModel(entity: CurrentWeather): CurrentWeatherModel {
        return CurrentWeatherModel(
            name = entity.name,
            weatherModelItems = entity.weatherItems?.map {
                WeatherModel(
                    it.id, it.main, it.description, it.icon
                )
            },
            mainModel = entity.main?.let {
                MainModel(
                    it.temp,
                    it.feelsLike,
                    it.tempMin,
                    it.tempMax,
                    it.pressure,
                    it.humidity,
                    it.seaLevel
                )
            },
            windModel = entity.wind?.let {
                WindModel(it.speed, it.deg, it.gust)
            },
            clouds = entity.clouds?.let {
                CloudModel(it.all)
            },
            sysModel = entity.sys?.let {
                SysModel(
                    it.type,
                    it.id,
                    it.country,
                    DateUtils.getCurrentDate(it.sunrise?.times(1000) ?: 0L),
                    DateUtils.getCurrentDate(it.sunset?.times(1000) ?: 0L)
                )
            },
            visibility = entity.visibility
        )
    }
}


