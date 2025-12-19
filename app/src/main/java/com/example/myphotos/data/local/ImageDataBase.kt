package com.example.myphotos.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myphotos.data.local.dao.ImageDao
import com.example.myphotos.data.local.entity.ImageEntity

@Database(entities = [ImageEntity::class], version = 1)
abstract class ImageDataBase : RoomDatabase() {

    abstract fun imageDao() : ImageDao

}