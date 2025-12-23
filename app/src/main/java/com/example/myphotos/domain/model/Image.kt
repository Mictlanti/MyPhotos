package com.example.myphotos.domain.model

data class ImageModel(
    val localId: Long,
    val remoteId: Int?,
    val title: String,
    val descriptionText: String,
    val contentUrl: String
)
