package com.spaceapp.core.di

import android.content.Context
import androidx.room.Room
import com.spaceapp.data.datasource.local.db.room.SpaceDatabase
import com.spaceapp.data.utils.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideSpaceDatabase(@ApplicationContext context: Context): SpaceDatabase {
        return Room.databaseBuilder(context, SpaceDatabase::class.java, RoomDatabase.SPACE_DB_NAME)
            .build()
    }
}