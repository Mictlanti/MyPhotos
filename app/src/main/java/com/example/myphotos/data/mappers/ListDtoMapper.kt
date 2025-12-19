package com.example.myphotos.data.mappers

import com.example.myphotos.data.remote.dto.ImageDto

fun List<ImageDto>.toImageModelList(descriptionText: String) = map { it.toImageModel(descriptionText) }