package com.example.myphotos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true) val localId : Long = 0L,
    val remoteId: Int? = null,
    val title: String,
    val descriptionText: String,
    val contentUrl: String
)