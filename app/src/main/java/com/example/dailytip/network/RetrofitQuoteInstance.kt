package com.example.dailytip.network

import com.example.dailytip.network.model.DailyQuote
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitQuoteInstance {

    private val retrofitQuote: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.quotable.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val dailyQuoteService: DailyApi by lazy {
        retrofitQuote.create(DailyApi::class.java)
    }
}

class DailyQuoteRepository {
    private val dailyQuoteService = RetrofitQuoteInstance.dailyQuoteService

    suspend fun getDailyQuote(): DailyQuote {
        return dailyQuoteService.getDailyQuotes()
    }
}