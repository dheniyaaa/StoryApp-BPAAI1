package com.example.submission1intermediate.ui.addstory

import androidx.lifecycle.ViewModel
import com.example.submission1intermediate.data.StoryRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart

class AddStoryViewModel(private val storyRepository: StoryRepository): ViewModel() {

    suspend fun uploadImage(token: String, file: MultipartBody.Part, description: RequestBody) = storyRepository.uploadStory(token, file, description)

    fun getAuthToken(): Flow<String?> = storyRepository.getAuthToken()
}