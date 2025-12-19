package com.example.myphotos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class ImageEntity(
    @PrimaryKey val id: Int = 0,
    val title: String,
    val descriptionText: String,
    val contentUrl: String
)