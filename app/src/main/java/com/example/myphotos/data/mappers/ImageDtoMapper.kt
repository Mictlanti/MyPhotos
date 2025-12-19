package com.example.myphotos.data.mappers

import com.example.myphotos.data.remote.dto.ImageDto
import com.example.myphotos.domain.model.ImageModel

fun ImageDto.toImageModel(descriptionText: String) = ImageModel(
    id = id,
    title = title,
    descriptionText = descriptionText,
    contentUrl = contentUrl
)