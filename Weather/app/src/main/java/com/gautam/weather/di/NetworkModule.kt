package com.gautam.weather.di

import com.gautam.core.fundamentals.Constants.BASE_URL
import com.gautam.core.fundamentals.Constants.CLIENT_MODULE
import com.gautam.core.fundamentals.Constants.TIME_OUT
import com.gautam.core.utils.NetworkUtils
import com.gautam.data.network.RestClientModule
import com.gautam.data.network.getClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single(named(CLIENT_MODULE)) {
        RestClientModule.build(
            BASE_URL,
            getClient(TIME_OUT, get())
        )
    }
    single {
        NetworkUtils(get())
    }

}