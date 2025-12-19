package com.example.myphotos.data.remote

import com.example.myphotos.data.local.dao.ImageDao
import com.example.myphotos.data.local.entity.ImageEntity
import javax.inject.Inject

class LocalDataSources @Inject constructor(
    private val imageDao: ImageDao
) {

    suspend fun insertImage(images: List<ImageEntity>) = imageDao.insertImage(images)

    fun getAllImages() : List<ImageEntity> = imageDao.getImages()

    fun getImageById(id: Int) : ImageEntity = imageDao.getImageById(id)

}