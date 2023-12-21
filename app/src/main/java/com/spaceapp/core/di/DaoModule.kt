package com.spaceapp.core.di

import com.spaceapp.data.datasource.local.apod.db.room.dao.ApodDao
import com.spaceapp.data.datasource.local.db.room.SpaceDatabase
import com.spaceapp.data.datasource.local.mars_photos.db.room.dao.MarsPhotoDao
import com.spaceapp.data.datasource.local.people_in_space.db.room.dao.PeopleInSpaceDao
import com.spaceapp.data.datasource.local.space_news.db.room.dao.ScienceNewsDao
import com.spaceapp.data.datasource.local.space_news.db.room.dao.SpaceNewsDao
import com.spaceapp.data.datasource.local.weather_condition.db.room.dao.WeatherConditionDao
import com.spaceapp.data.datasource.local.where_is_the_iss.db.room.dao.IssDao
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

    @Singleton
    @Provides
    fun provideScienceDao(spaceDataBase: SpaceDatabase): ScienceNewsDao =
        spaceDataBase.scienceNewsDao()
}