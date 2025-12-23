package com.example.myphotos.data.remote

import android.util.Log
import com.example.myphotos.data.remote.api.ApiService
import com.example.myphotos.data.remote.dto.ImageDto
import com.example.myphotos.domain.util.Result
import javax.inject.Inject

class RemoteDataSources @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getImages() : Result<List<ImageDto>> = try {
        Result.Success(apiService.getImages())
    } catch (e: Exception) {
        Result.Error(msg = e.message ?: "Error to upload data")
    }

    suspend fun getDescriptionText(url: String): Result<String> = try {
        Result.Success(apiService.getRawText(url).string())
    } catch (e: Exception) {
        Result.Error(msg = e.localizedMessage ?: "Error to upload description")
    }

    suspend fun createImage(dto: ImageDto) : Result<ImageDto> = try {
        Result.Success(apiService.createImage(dto))
    } catch (e: Exception) {
        Result.Error(msg = e.message ?: "Error to create data")
    }
}