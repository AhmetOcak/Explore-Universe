package com.spaceapp.core.di

import com.spaceapp.data.datasource.local.db.room.SpaceDatabase
import com.spaceapp.data.datasource.local.db.room.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    fun provideApodDao(spaceDataBase: SpaceDatabase): ApodDao =
        spaceDataBase.apodDao()

    @Singleton
    @Provides
    fun provideMarsPhotoDao(spaceDataBase: SpaceDatabase): MarsPhotoDao =
        spaceDataBase.marsPhotoDao()

    @Singleton
    @Provides
    fun providePeopleInSpaceDao(spaceDataBase: SpaceDatabase): PeopleInSpaceDao =
        spaceDataBase.peopleInSpaceDao()

    @Singleton
    @Provides
    fun provideSpaceNewsDao(spaceDataBase: SpaceDatabase): SpaceNewsDao =
        spaceDataBase.spaceNewsDao()

    @Singleton
    @Provides
    fun provideWeatherConditionDao(spaceDataBase: SpaceDatabase): WeatherConditionDao =
        spaceDataBase.weatherConditionDao()

    @Singleton
    @Provides
    fun provideIssDao(spaceDataBase: SpaceDatabase): IssDao =
        spaceDataBase.issDao()
}