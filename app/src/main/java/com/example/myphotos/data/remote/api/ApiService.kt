package com.example.myphotos.data.remote.api

import com.example.myphotos.data.remote.dto.ImageDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("Mictlanti/MYPhotosApi/refs/heads/main/Json/MyJSON.json")
    suspend fun getImages() : List<ImageDto>

    @GET
    suspend fun getRawText(@Url url: String) : ResponseBody

}