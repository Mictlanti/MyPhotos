package com.example.myphotos.domain.state

import com.example.myphotos.domain.model.ImageModel

data class ImageState(
    val listImages : List<ImageModel> = emptyList(),
    val imageModel: ImageModel? = null
)