package com.example.dictionary.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionary.model.DictionaryData
import com.example.dictionary.model.RetrofitInstance
import kotlinx.coroutines.launch

class DictionaryViewModel: ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    var wordData by mutableStateOf<DictionaryData?>(null)
        private set

    fun searchWord(word: String) {
        if(word.isBlank()) return

        viewModelScope.launch {
            isLoading = true
            try {
                val respose = RetrofitInstance.api.getWordMeaning(word)
                wordData = respose.firstOrNull()
            } catch (e: Exception) {
                wordData = null
            }
            isLoading = false
        }
    }
}