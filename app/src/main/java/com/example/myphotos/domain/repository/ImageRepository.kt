package com.example.myphotos.domain.repository

import com.example.myphotos.domain.model.ImageModel

interface ImageRepository {

    suspend fun getImages() : List<ImageModel>

    suspend fun getImageById(id: Int) : ImageModel?

}