package com.example.myphotos.data.remote

import android.util.Log
import com.example.myphotos.data.remote.api.ApiService
import com.example.myphotos.data.remote.dto.ImageDto
import javax.inject.Inject

class RemoteDataSources @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getImages() : List<ImageDto> = try {
        apiService.getImages()
    } catch (e: Exception) {
        Log.e("RemoteData", "error get images: ${e.message}")
        emptyList()
    }

    suspend fun getDescriptionText(url: String): String = try {
        apiService.getRawText(url).string()
    } catch (e: Exception) {
        Log.e("RemoteData", "error get description: ${e.message}")
        "null"
    }
}