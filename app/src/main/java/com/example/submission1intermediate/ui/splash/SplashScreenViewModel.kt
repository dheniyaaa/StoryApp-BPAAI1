package com.example.submission1intermediate.ui.splash

import androidx.lifecycle.ViewModel
import com.example.submission1intermediate.data.StoryRepository
import kotlinx.coroutines.flow.Flow

class SplashScreenViewModel(private val storyRepository: StoryRepository) : ViewModel() {

    fun getAuthToken(): Flow<String?> = storyRepository.getAuthToken()

}