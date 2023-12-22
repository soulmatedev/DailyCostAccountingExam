package com.example.expenseexam.network

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GetJokeAPI : ViewModel() {
    init {
        getJoke()
    }
    private fun getJoke() {
        viewModelScope.launch {
            val listResult = jokeAPIService.getJoke()
        }
    }
}