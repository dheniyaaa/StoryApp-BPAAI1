package com.example.submission1intermediate.data

import androidx.lifecycle.LiveData
import com.example.submission1intermediate.data.remote.*
import com.example.submission1intermediate.vstate.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface StoryDataSource {

    suspend fun register(name: String, email: String, password: String): LiveData<Resource<ApiResponse<RegisterResponse>>>

    suspend fun login (email: String, password: String): LiveData<Resource<ApiResponse<LoginResponse>>>

    suspend fun getAllStory(token:String): LiveData<Resource<ApiResponse<StoryResponse>>>

    suspend fun uploadStory(token: String, file: MultipartBody.Part, description: RequestBody): LiveData<Resource<ApiResponse<FileUploadResponse>>>

    suspend fun saveAuthToken(token: String)

    fun getAuthToken(): Flow<String?>
}