package com.example.submission1intermediate.ui.register

import androidx.lifecycle.ViewModel
import com.example.submission1intermediate.data.StoryRepository

class RegisterViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    suspend fun userRegister(name: String, email: String, password: String) = storyRepository.register(name, email, password)
}