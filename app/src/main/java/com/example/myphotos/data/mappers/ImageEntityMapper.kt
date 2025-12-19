package com.example.myphotos.data.mappers

import com.example.myphotos.data.local.entity.ImageEntity
import com.example.myphotos.data.remote.dto.ImageDto
import com.example.myphotos.domain.model.ImageModel

fun ImageDto.toEntity(descriptionText: String) : ImageEntity = ImageEntity(
    id = id,
    title = title,
    descriptionText = descriptionText,
    contentUrl = contentUrl
)

fun ImageEntity.toDomain() : ImageModel = ImageModel(
    id = id,
    title = title,
    descriptionText = descriptionText,
    contentUrl = contentUrl
)