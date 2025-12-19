package com.example.myphotos.domain.repository

import com.example.myphotos.data.local.entity.ImageEntity

interface ImageRepository {

    suspend fun getImages() : List<ImageEntity>

    suspend fun getImageById(id: Int) : ImageEntity

}