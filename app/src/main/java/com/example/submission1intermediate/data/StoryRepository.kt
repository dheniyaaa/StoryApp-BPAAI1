package com.example.submission1intermediate.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.submission1intermediate.data.local.AuthPreference
import com.example.submission1intermediate.data.remote.*
import com.example.submission1intermediate.vstate.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

class StoryRepository(
    private val authPreference: AuthPreference,
    private val remoteDataSource: RemoteDataSource
): StoryDataSource {

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ) = liveData(Dispatchers.IO){
        val userRegister = remoteDataSource.register(name, email, password)
        try {
            emit(Resource.success(userRegister))
        } catch (e: Exception){
            e.printStackTrace()
            emit(Resource.error("$e", userRegister))
        }
    }

    override suspend fun login(email: String, password: String) = liveData(Dispatchers.IO) {

        val userLogin = remoteDataSource.login(email, password)
        try {
            emit(Resource.success(userLogin))
        } catch (e: Exception){
            emit(Resource.error("$e", userLogin))
        }

    }

    override suspend fun getAllStory(token: String): LiveData<Resource<ApiResponse<StoryResponse>>> = liveData(Dispatchers.IO){
        val stories = remoteDataSource.getAllStory(token)

        try {
            emit(Resource.success(stories))
        } catch (e: Exception){
            e.printStackTrace()
            emit(Resource.error("$e", stories))
        }


    }

    override suspend fun uploadStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody
    ) = liveData(Dispatchers.IO){
        val userUpload = remoteDataSource.uploadStory(token, file, description)
        try {
            emit(Resource.success(userUpload))
        } catch (e: Exception){
            e.printStackTrace()
            emit(Resource.error("$e", userUpload))
        }
    }

    override suspend fun saveAuthToken(token: String) {
        authPreference.saveAuthToken(token)
    }

    override fun getAuthToken(): Flow<String?> = authPreference.getAuthToken()




}