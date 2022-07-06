package com.example.submission1intermediate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submission1intermediate.data.StoryRepository
import kotlinx.coroutines.launch

class MainViewModel(private val storyRepository: StoryRepository): ViewModel() {

    fun saveAuthToken(token: String) {
        viewModelScope.launch { storyRepository.saveAuthToken(token) }
    }

    suspend fun getAllStory(token: String) = storyRepository.getAllStory(token)
}