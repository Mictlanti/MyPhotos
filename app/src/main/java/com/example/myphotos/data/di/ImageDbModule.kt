package com.example.myphotos.data.di

import android.content.Context
import androidx.room.Room
import com.example.myphotos.data.local.ImageDataBase
import com.example.myphotos.data.local.dao.ImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageDbModule {

    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext context: Context
    ) : ImageDataBase {
        return Room.databaseBuilder(
            context,
            ImageDataBase::class.java,
            "Image_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(db: ImageDataBase) : ImageDao = db.imageDao()

}