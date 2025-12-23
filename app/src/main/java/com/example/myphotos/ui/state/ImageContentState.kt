package com.example.myphotos.ui.state

import com.example.myphotos.domain.model.ImageModel

data class ImageContentState(
    val isLoading: Boolean = false,
    val errorMsg: String? = null,
    val listImages : List<ImageModel> = emptyList(),
    val imageModel: ImageModel? = null
)
