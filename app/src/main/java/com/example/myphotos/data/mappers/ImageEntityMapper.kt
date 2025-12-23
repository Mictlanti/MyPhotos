package com.example.myphotos.data.mappers

import com.example.myphotos.data.local.entity.ImageEntity
import com.example.myphotos.data.remote.dto.ImageDto
import com.example.myphotos.domain.model.ImageModel

fun ImageEntity.toDto() : ImageDto = ImageDto(
    id = 0,
    title = title,
    descriptionUrl = descriptionText,
    contentUrl = contentUrl
)
fun ImageDto.toEntity(descriptionText: String) : ImageEntity = ImageEntity(
    remoteId = id,
    title = title,
    descriptionText = descriptionText,
    contentUrl = contentUrl
)

fun ImageEntity.toDomain() : ImageModel = ImageModel(
    remoteId = remoteId,
    localId = localId,
    title = title,
    descriptionText = descriptionText,
    contentUrl = contentUrl
)