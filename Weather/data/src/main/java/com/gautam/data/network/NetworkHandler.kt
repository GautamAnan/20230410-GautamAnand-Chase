package com.gautam.data.network

import android.content.Context
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

val loggingInterceptor = HttpLoggingInterceptor()
    .apply { level = HttpLoggingInterceptor.Level.BODY }

/**
 * Get client
 *
 * @param timeOut
 * @param context
 * @param listInterceptor
 * @return
 */
fun getClient(
    timeOut: Long,
    context: Context,
    listInterceptor: List<Interceptor> = listOf(loggingInterceptor)
): OkHttpClient {
    return OkHttpClient.Builder()
        .cache(getCache(context))
        .readTimeout(timeOut, TimeUnit.SECONDS)
        .writeTimeout(timeOut, TimeUnit.SECONDS)
        .connectTimeout(timeOut, TimeUnit.SECONDS)
        .apply { listInterceptor.forEach { addInterceptor(it) } }
        .build()
}

private fun getCache(context: Context) = Cache(context.cacheDir, (10 * 1024 * 1024).toLong())
