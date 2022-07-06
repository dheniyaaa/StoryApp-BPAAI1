package com.example.submission1intermediate.data.remote

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)