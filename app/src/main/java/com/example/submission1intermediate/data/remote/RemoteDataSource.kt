package com.example.submission1intermediate.data.remote

import okhttp3.MultipartBody
import okhttp3.RequestBody

class RemoteDataSource {


    suspend fun register(
        name: String,
        email: String,
        password: String
    ): ApiResponse<RegisterResponse>{
        val response = ApiConfig.getApiService().register(name, email, password)
        return try {
            ApiResponse.success(response)
        } catch (e: Exception){
            ApiResponse.error("$e", response )
        }
    }

    suspend fun login(email: String, password: String): ApiResponse<LoginResponse>{
        val response = ApiConfig.getApiService().login(email, password)
        return try {
            ApiResponse.success(response)
        } catch (e: Exception){
            ApiResponse.error("$e", response)
        }
    }

    suspend fun getAllStory(token:String): ApiResponse<StoryResponse>{
        val bToken = "Bearer " +token
        val response =ApiConfig.getApiService().getAllStories(bToken)

        return try {
            ApiResponse.success(response)

        } catch (e: Exception){
            ApiResponse.error("$e", response)
        }
    }

    suspend fun uploadStory(token: String, file: MultipartBody.Part , description: RequestBody): ApiResponse<FileUploadResponse>{

        val bearerToken = "Bearer " +token
        val response = ApiConfig.getApiService().uploadStory(bearerToken, file, description)
        return try {
            ApiResponse.success(response)

        } catch ( e: Exception){
            ApiResponse.error("$e", response)
        }
    }

}