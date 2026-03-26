package com.example.my_shop.data.remote.cloudinary

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class CloudinaryRepository {

    suspend fun uploadImage(context: Context, uri: Uri): String? {

        return try {

            val inputStream = context.contentResolver.openInputStream(uri)
            val bytes = inputStream?.readBytes()

            val requestFile = bytes?.toRequestBody("image/*".toMediaTypeOrNull())

            val body = requestFile?.let {
                MultipartBody.Part.createFormData("file", "image.jpg", it)
            }

            val preset = "my_preset"
                .toRequestBody("text/plain".toMediaTypeOrNull())

            val response = body?.let {
                CloudinaryInstance.api.uploadImage(it, preset)
            }

            response?.secure_url

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}