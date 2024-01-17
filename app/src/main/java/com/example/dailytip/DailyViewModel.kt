package com.example.dailytip

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailytip.network.DailyImage
import com.example.dailytip.network.DailyImageRepository
import com.example.dailytip.network.DailyQuote
import com.example.dailytip.network.DailyQuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DailyViewModel: ViewModel() {
    private val apiQuoteService = DailyQuoteRepository()
    private val apiImageService = DailyImageRepository()

    var dailyQuote by mutableStateOf<DailyQuote?>(null)
    var dailyImage by mutableStateOf<DailyImage?>(null)

    private val _uiState = MutableStateFlow(DailyUiState())
    val uiState: StateFlow<DailyUiState> = _uiState

    fun getQuotes() {
        viewModelScope.launch {
            try {
                val response = apiQuoteService.getDailyQuote()
                dailyQuote = response
            } catch (e: Exception) {
                Log.e("Network", "ERROR")
            }
        }
    }

    fun getImages() {
        viewModelScope.launch {
            try {
                val response = apiImageService.getDailyImage()
                dailyImage = response
            } catch (e: Exception) {
                Log.e("Network", "ERROR")
            }
        }
    }
}
data class DailyUiState (
    var numberDay: Int = 0,
    var dailyQuote: DailyQuote? = null,
    var dailyImage: DailyImage? = null
)