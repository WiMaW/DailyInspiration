package com.example.dailytip.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitImageInstance {
    private val retrofitImage: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://picsum.photos")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val dailyImageService: DailyApi by lazy {
        retrofitImage.create(DailyApi::class.java)
    }
}

class DailyImageRepository {
    private val dailyImageService = RetrofitImageInstance.dailyImageService

    suspend fun getDailyImage(): DailyImage {
        return dailyImageService.getDailyImage()
    }
}