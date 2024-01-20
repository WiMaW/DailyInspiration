package com.example.dailytip

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailytip.data.dailyImageList

import com.example.dailytip.network.model.DailyQuote
import com.example.dailytip.network.DailyQuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException


class DailyViewModel: ViewModel() {
    private val apiQuoteService = DailyQuoteRepository()

    var dailyQuote by mutableStateOf<DailyQuote?>(null)

    private val _uiState = MutableStateFlow(DailyUiState())
    val uiState: StateFlow<DailyUiState> = _uiState

    fun getQuotes() {
        viewModelScope.launch {
            try {
                val response = apiQuoteService.getDailyQuote()
                dailyQuote = response
            } catch (e: IOException ) {
                networkProblemsInfoForUser()
            } catch (e: HttpException) {
                networkProblemsInfoForUser()
            }
        }
    }

    fun getImage() : String {
        return dailyImageList.random().imageUri
    }


    fun updateView(number: Int) {
        _uiState.update {
            it.copy(numberClicked = number)
        }
    }

    private fun networkProblemsInfoForUser() {
        _uiState.update {
            it.copy(networkProblems = true)
        }
    }
}
data class DailyUiState (
    var numberClicked: Int = 0,
    var dailyQuote: DailyQuote? = null,
    var networkProblems: Boolean = false
)