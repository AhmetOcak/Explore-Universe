package com.spaceapp.core.common

import android.content.Context
import coil.ImageLoader
import coil.decode.GifDecoder

object GifLoader {
    fun gifLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .components {
                add(GifDecoder.Factory())
            }.build()
    }
}