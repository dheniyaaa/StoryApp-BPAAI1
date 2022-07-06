package com.example.submission1intermediate.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submission1intermediate.data.StoryRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val storyRepository: StoryRepository) : ViewModel() {
    suspend fun userLogin(email: String, password: String) = storyRepository.login(email, password)

    fun saveAuthToken(token: String){
        viewModelScope.launch { storyRepository.saveAuthToken(token) }

    }
}