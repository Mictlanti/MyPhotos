package com.example.myphotos.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object ImageHome : Routes
    @Serializable
    data class ImageView(val id: Long) : Routes
    @Serializable
    data object AddImage : Routes
}