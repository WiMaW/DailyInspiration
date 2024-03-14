package com.example.dailytip

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailytip.data.dailyImageList
import com.example.dailytip.network.DailyQuoteRepository
import com.example.dailytip.network.model.DailyQuote
import com.example.dailytip.network.model.DailyInspirationOperationStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException


class DailyViewModel : ViewModel() {
    private val apiQuoteService = DailyQuoteRepository()

    var dailyQuote by mutableStateOf<DailyQuote?>(null)
    var getDailyInspiration by mutableStateOf(DailyInspirationOperationStatus.UNKNOWN)

    private val _uiState = MutableStateFlow(DailyUiState())
    val uiState: StateFlow<DailyUiState> = _uiState

    fun getQuotes() {
        viewModelScope.launch {
            try {
                getDailyInspiration = DailyInspirationOperationStatus.LOADING
                val response = apiQuoteService.getDailyQuote()
                dailyQuote = response
                getDailyInspiration = DailyInspirationOperationStatus.SUCCESS
            } catch (e: IOException) {
                getDailyInspiration = DailyInspirationOperationStatus.ERROR
            } catch (e: HttpException) {
                getDailyInspiration = DailyInspirationOperationStatus.ERROR
            }
        }
    }

    fun getImage(): String {
        try {
            getDailyInspiration = DailyInspirationOperationStatus.LOADING
            return dailyImageList.random().imageUri
            getDailyInspiration = DailyInspirationOperationStatus.SUCCESS
        } catch (e: IOException) {
            getDailyInspiration = DailyInspirationOperationStatus.ERROR
            return ""
        } catch (e: HttpException) {
            getDailyInspiration = DailyInspirationOperationStatus.ERROR
            return ""
        }
    }


    fun updateView(number: Int) {
        _uiState.update {
            it.copy(numberClicked = number)
        }
    }
}

data class DailyUiState(
    var numberClicked: Int = 0,
    var dailyQuote: DailyQuote? = null,
)