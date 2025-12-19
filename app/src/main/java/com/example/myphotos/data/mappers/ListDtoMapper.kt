package com.example.myphotos.data.mappers

import com.example.myphotos.data.local.entity.ImageEntity
import com.example.myphotos.data.remote.dto.ImageDto
import com.example.myphotos.domain.model.ImageModel

fun List<ImageDto>.toImageModelList(descriptionText: String) = map { it.toImageModel(descriptionText) }

fun List<ImageEntity>.toImageDomainEntity() : List<ImageModel> = map { it.toDomain() }