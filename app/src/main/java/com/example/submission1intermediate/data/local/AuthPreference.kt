package com.example.submission1intermediate.data.local

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthPreference (application: Application) {

    private val TOKEN_KEY = stringPreferencesKey("token_key")
    private val Context.dataStoreToken by preferencesDataStore("auth")
    private val dataStoreToken = application.dataStoreToken

    fun getAuthToken(): Flow<String?> {
        return dataStoreToken.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    suspend fun saveAuthToken(token: String){
        dataStoreToken.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }


}