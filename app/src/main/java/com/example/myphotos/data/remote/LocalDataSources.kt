package com.example.myphotos.data.remote

import com.example.myphotos.data.local.dao.ImageDao
import com.example.myphotos.data.local.entity.ImageEntity
import javax.inject.Inject

class LocalDataSources @Inject constructor(
    private val imageDao: ImageDao
) {

    suspend fun insertImageFromRemote(images: List<ImageEntity>) = imageDao.insertImageFromRemote(images)

    suspend fun insertUserImage(image: ImageEntity) = imageDao.insertUserImage(image)

    suspend fun getAllImages() : List<ImageEntity> = imageDao.getImages()

    suspend fun getImageById(id: Long) : ImageEntity? = imageDao.getImageById(id)

    suspend fun getImagesPending() : List<ImageEntity> = imageDao.getImagesPending()

    suspend fun updateImage(image: ImageEntity) = imageDao.updateImages(image)

    fun updateSyncState(id: Long, remoteId: Int?) = imageDao.updateSyncState(id, remoteId)

    fun deleteRemote(localId: Long) = imageDao.delete(localId)

}