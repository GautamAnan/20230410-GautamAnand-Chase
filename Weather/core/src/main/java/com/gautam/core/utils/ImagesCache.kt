package com.gautam.core.utils

import android.graphics.Bitmap
import android.util.LruCache

object ImagesCache {
    private var imagesWarehouse: LruCache<String, Bitmap>? = null
    init {
        initializeCache()
    }
    private fun initializeCache() {
        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        val cacheSize = maxMemory / 8
        println("cache size = $cacheSize")
        imagesWarehouse = object : LruCache<String, Bitmap>(cacheSize) {
            protected override fun sizeOf(key: String?, value: Bitmap): Int {
                // The cache size will be measured in kilobytes rather than number of items.
                val bitmapByteCount = value.rowBytes * value.height
                return bitmapByteCount / 1024
            }
        }
    }

    fun addImageToWarehouse(key: String?, value: Bitmap?) {
        if (imagesWarehouse != null && imagesWarehouse?.get(key) == null) {
            imagesWarehouse?.put(key, value)
        }
    }

    fun getImageFromWarehouse(key: String?): Bitmap? {
        return if (key != null) {
            imagesWarehouse?.get(key)
        } else {
            null
        }
    }

    fun removeImageFromWarehouse(key: String?) {
        imagesWarehouse?.remove(key)
    }

    fun clearCache() {
        if (imagesWarehouse != null) {
            imagesWarehouse?.evictAll()
        }
    }

}