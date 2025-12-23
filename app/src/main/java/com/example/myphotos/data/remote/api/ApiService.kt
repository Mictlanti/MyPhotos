package com.example.myphotos.data.remote.api

import com.example.myphotos.data.remote.dto.ImageDto
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url
import javax.annotation.processing.Generated

interface ApiService {

    @GET("Mictlanti/MYPhotosApi/refs/heads/main/Json/MyJSON.json")
    suspend fun getImages() : List<ImageDto>
    @GET
    suspend fun getRawText(@Url url: String) : ResponseBody
    //Endpoint hipotético para la escritura en API
    @POST
    suspend fun createImage(@Body dto: ImageDto) : ImageDto

}