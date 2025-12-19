package com.example.myphotos.data.repositoryImpl

import android.util.Log
import com.example.myphotos.data.mappers.toImageModel
import com.example.myphotos.data.mappers.toImageModelList
import com.example.myphotos.data.remote.RemoteDataSources
import com.example.myphotos.domain.model.ImageModel
import com.example.myphotos.domain.repository.ImageRepository
import javax.inject.Inject

class ImageRepoImpl @Inject constructor(
    private val remoteDataSources: RemoteDataSources
) : ImageRepository {

    override suspend fun getImages(): List<ImageModel> {
        return try {
            val remoteImages = remoteDataSources.getImages()

            remoteImages.map { dto ->
                val descriptionText = remoteDataSources.getDescriptionText(dto.descriptionUrl)

                dto.toImageModel(descriptionText)
            }

        } catch (e: Exception) {
            Log.e("ImageRepoImplementation", "Error converting data: ${e.localizedMessage}")
            emptyList()
        }
    }

    override suspend fun getImageById(id: Int): ImageModel? {
        return try {
            val images = remoteDataSources.getImages()

            val dto = images.firstOrNull { it.id == id }
                ?: return null

            val descriptionText =
                remoteDataSources.getDescriptionText(dto.descriptionUrl)

            dto.toImageModel(descriptionText)

        } catch (e: Exception) {
            Log.e("ImageRepoImplementation", "Error getting image by id", e)
            null
        }
    }

}