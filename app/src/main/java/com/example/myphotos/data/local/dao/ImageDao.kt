package com.example.myphotos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myphotos.data.local.entity.ImageEntity

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(images: List<ImageEntity>)

    @Query("SELECT * FROM image_table")
    fun getImages() : List<ImageEntity>

    @Query("SELECT * FROM image_table WHERE id = :id")
    fun getImageById(id: Int) : ImageEntity

}