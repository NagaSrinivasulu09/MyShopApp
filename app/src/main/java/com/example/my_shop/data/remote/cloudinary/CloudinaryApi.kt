package com.example.my_shop.data.remote.cloudinary

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CloudinaryApi {
    @Multipart
    @POST("v1_1/dhaa3bcqi/image/upload")
    suspend fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("upload_preset") preset: RequestBody
    ): CloudinaryResponse
}