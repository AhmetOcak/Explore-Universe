package com.spaceapp.core.common.helper

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import coil.memory.MemoryCache

object ImageLoader {
    fun load(context: Context): ImageLoader.Builder {
        return ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder(context).build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache"))
                    .build()
            }
    }
}