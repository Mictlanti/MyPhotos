package com.example.myphotos.data.repositoryImpl

import com.example.myphotos.data.local.entity.ImageEntity
import com.example.myphotos.data.mappers.toDto
import com.example.myphotos.data.mappers.toEntity
import com.example.myphotos.data.remote.LocalDataSources
import com.example.myphotos.data.remote.RemoteDataSources
import com.example.myphotos.domain.repository.ImageRepository
import com.example.myphotos.domain.util.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageRepoImpl @Inject constructor(
    private val remoteDataSources: RemoteDataSources,
    private val localDataSources: LocalDataSources
) : ImageRepository {

    override suspend fun getImages(): Result<List<ImageEntity>> {

        val localImages = localDataSources.getAllImages()
        if (localImages.isNotEmpty()) {
            return Result.Success(localImages)
        }

        return when (val remoteResult = remoteDataSources.getImages()) {

            is Result.Success -> {
                val remoteImages = remoteResult.data ?: emptyList()
                val entities = remoteImages.map { dto ->
                    val descriptionResult =
                        remoteDataSources.getDescriptionText(dto.descriptionUrl)

                    val description = when (descriptionResult) {
                        is Result.Success -> descriptionResult.data ?: ""
                        is Result.Error -> ""
                    }

                    dto.toEntity(description)
                }

                localDataSources.insertImageFromRemote(entities)
                Result.Success(entities)
            }

            is Result.Error -> {
                Result.Error(remoteResult.msg ?: "Unknow error")
            }
        }
    }


    override suspend fun getImageById(id: Long): Result<ImageEntity> {
        val image = localDataSources.getImageById(id)
        return if(image != null) {
            Result.Success(image)
        } else {
            Result.Error("Image not found")
        }
    }

    override suspend fun createImage(imageEntity: ImageEntity) {
        localDataSources.insertUserImage(imageEntity)
        tryUploadPending()
    }

    private suspend fun tryUploadPending() {
        val pending = localDataSources.getImagesPending()

        pending.forEach { imageEntity ->
            if (imageEntity.remoteId == null) {
                handlePendingCreate(imageEntity)
            }
        }
    }


    private suspend fun handlePendingCreate(imageEntity: ImageEntity) {
        val dto = imageEntity.toDto()
        when (val create = remoteDataSources.createImage(dto)) {
            is Result.Success -> {
                val remoteDto = create.data

                val syncEntity = imageEntity.copy(
                    remoteId = remoteDto?.id,
                )

                localDataSources.updateImage(syncEntity)
            }

            is Result.Error -> {
                localDataSources.updateSyncState(imageEntity.localId, remoteId = null)
            }
        }
    }

}