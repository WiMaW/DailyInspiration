package com.example.dailytip.network

import retrofit2.http.GET

interface DailyApi {
    @GET("/random")
    suspend fun getDailyQuotes() : DailyQuote

    @GET("/200/300")
    suspend fun getDailyImage() : DailyImage
}