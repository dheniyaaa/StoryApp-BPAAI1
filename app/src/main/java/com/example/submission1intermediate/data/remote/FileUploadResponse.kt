package com.example.submission1intermediate.data.remote

import com.google.gson.annotations.SerializedName

data class FileUploadResponse (

    @field:SerializedName("error")
    val error: Boolean? = null,

    @field:SerializedName("message")
    val message: String? = null
)