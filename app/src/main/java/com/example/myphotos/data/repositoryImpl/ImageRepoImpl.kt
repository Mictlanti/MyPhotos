package com.example.myphotos.data.repositoryImpl

import android.util.Log
import com.example.myphotos.data.local.entity.ImageEntity
import com.example.myphotos.data.mappers.toEntity
import com.example.myphotos.data.mappers.toImageModel
import com.example.myphotos.data.mappers.toImageModelList
import com.example.myphotos.data.remote.LocalDataSources
import com.example.myphotos.data.remote.RemoteDataSources
import com.example.myphotos.domain.model.ImageModel
import com.example.myphotos.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepoImpl @Inject constructor(
    private val remoteDataSources: RemoteDataSources,
    private val localDataSources: LocalDataSources
) : ImageRepository {

    override suspend fun getImages(): List<ImageEntity> {

        val localImages = localDataSources.getAllImages()
        if(localImages.isNotEmpty()) {
            return localImages
        }

        return try {
            val remoteImages = remoteDataSources.getImages()

            val entities = remoteImages.map { dto ->
                val descriptionText = remoteDataSources.getDescriptionText(dto.descriptionUrl)

                dto.toEntity(descriptionText)
            }

            localDataSources.insertImage(entities)

            entities

        } catch (e: Exception) {
            Log.e("ImageRepoImplementation", "Error converting data: ${e.localizedMessage}")
            emptyList()
        }
    }

    override suspend fun getImageById(id: Int): ImageEntity {
        return localDataSources.getImageById(id)
    }

}