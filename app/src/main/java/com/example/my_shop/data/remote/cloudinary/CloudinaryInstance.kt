package com.example.my_shop.data.remote.cloudinary

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CloudinaryInstance {
    val api: CloudinaryApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.cloudinary.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CloudinaryApi::class.java)
    }
}