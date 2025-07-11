package com.example.detectImageContent

import android.content.Context
import android.graphics.Bitmap
import coil3.ImageLoader
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.request.allowHardware
import coil3.toBitmap

class GetImageBitmap(private val context: Context) {

    suspend fun getImageBitmapFromUrl(url: String): Bitmap {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(url)
            .allowHardware(false)
            .build()

        val result = loader.execute(request)
        return if (result is SuccessResult) {
            result.image.toBitmap()
        } else {
            throw Exception("Failed to load image from URL: $url")
        }
    }
}
