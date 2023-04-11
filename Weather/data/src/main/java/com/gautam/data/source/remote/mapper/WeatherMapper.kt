package com.gautam.data.source.remote.mapper

import com.gautam.data.entity.CurrentWeather
import com.gautam.data.entity.OneCallResponse
import com.gautam.domain.model.*


class WeatherMapper {
    fun toOneCallResponseModel(entity: OneCallResponse): OneCallResponseModel {
        return OneCallResponseModel(currentModel = entity.current?.let {
            CurrentModel(
                it.dt,
                it.sunrise,
                it.sunset,
                it.temp,
                it.feelsLike,
                it.pressure,
                it.humidity,
                it.dewPoint,
                it.uvi,
                it.clouds,
                it.visibility,
                it.windSpeed,
                it.windDeg
            )
        }, hourlyModel = entity.hourly?.map {
            HourlyModel(
                it.dt,
                it.temp,
                it.feelsLike,
                it.pressure,
                it.humidity,
                it.dewPoint,
                it.uvi,
                it.clouds,
                it.visibility,
                it.windSpeed,
                it.windDeg,
                it.windGust,
            )
        }, dailyModel = entity.daily?.map {
            DailyModel(
                it.dt,
                it.sunrise,
                it.sunset,
                it.moonrise,
                it.moonset,
                it.moonPhase,
                it.temp?.let {
                    TempModel(
                        it.dt, it.min, it.max, it.night, it.eve, it.morn
                    )
                },
                it.feelsLike?.let {
                    FeelLikeModel(it.day, it.night, it.eve, it.morn)
                },
                it.pressure,
                it.humidity,
                it.dewPoint,
                it.windSpeed,
                it.windDeg,
                it.windGust,
                it.weather?.map {
                    WeatherModel(
                        it.id, it.main, it.description, it.icon
                    )
                },
                it.clouds,
                it.pop,
                it.uvi
            )
        }
        )
    }

    fun toCurrentWeatherModel(entity: CurrentWeather): CurrentWeatherModel {
        return CurrentWeatherModel(
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
                SysModel(it.type, it.id, it.country, it.sunrise, it.sunset)
            }
        )
    }
}


