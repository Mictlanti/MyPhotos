package com.example.myphotos.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myphotos.data.local.entity.ImageEntity

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImageFromRemote(images: List<ImageEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserImage(image: ImageEntity)

    @Query("SELECT * FROM image_table")
    suspend fun getImages() : List<ImageEntity>

    @Query("SELECT * FROM image_table WHERE localId = :id")
    suspend fun getImageById(id: Long) : ImageEntity?

    @Query("SELECT * FROM image_table WHERE remoteId == :pending")
    suspend fun getImagesPending(pending: Int? = null) : List<ImageEntity>

    @Update
    suspend fun updateImages(image: ImageEntity)

    @Query("UPDATE image_table SET remoteId = :remoteId WHERE localId = :id")
    fun updateSyncState(id: Long, remoteId: Int?)

    @Query("DELETE FROM image_table WHERE localId = :localId")
    fun delete(localId: Long)


}