package com.example.myphotos.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    val id : Int,
    val title: String,
    @SerializedName("description_url") val descriptionUrl : String,
    @SerializedName ("content_url") val contentUrl : String
)