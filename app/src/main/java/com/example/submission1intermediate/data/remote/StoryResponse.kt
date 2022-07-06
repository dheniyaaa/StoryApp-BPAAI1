package com.example.submission1intermediate.data.remote

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class StoryResponse(
    @field:SerializedName("listStory")
    val stories: List<Story>,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

@Parcelize
data class Story (
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("photoUrl")
    val photoUrl: String,

    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("id")
    val id: String

) : Parcelable
