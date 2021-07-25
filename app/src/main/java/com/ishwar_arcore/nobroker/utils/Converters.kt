package com.ishwar_arcore.nobroker.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

class Converters {

    /**
     * converts bitmap to byte array
     * used by database for conversion
     * */
    @TypeConverter
    fun fromBitmap(bitmap: Bitmap): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    /**
     * converts byte array to bitmap
     * used by database for conversion
     * */
    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    /**
     * Getting a bitmap image
     * */
    companion object {
        suspend fun getBitmap(toString: String, context: Context): Bitmap {
            val loading = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(toString)
                .build()
            val result = (loading.execute(request) as SuccessResult).drawable
            return (result as BitmapDrawable).bitmap
        }
    }
}