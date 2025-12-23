package com.example.myphotos.domain.repository

import com.example.myphotos.data.local.entity.ImageEntity
import com.example.myphotos.domain.util.Result

interface ImageRepository {

    suspend fun getImages() : Result<List<ImageEntity>>

    suspend fun getImageById(id: Long) : Result<ImageEntity>

    suspend fun createImage(imageEntity: ImageEntity)

}