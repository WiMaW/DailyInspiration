package com.example.dailytip.network

import com.example.dailytip.network.model.DailyQuote
import retrofit2.http.GET

interface DailyApi {
    @GET("/random")
    suspend fun getDailyQuotes() : DailyQuote
}