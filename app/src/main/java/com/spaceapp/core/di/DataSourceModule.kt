package com.spaceapp.core.di

import com.spaceapp.data.datasource.local.apod.*
import com.spaceapp.data.datasource.local.apod.db.room.dao.ApodDao
import com.spaceapp.data.datasource.local.explore_galaxy_data.*
import com.spaceapp.data.datasource.local.glossary_data.*
import com.spaceapp.data.datasource.local.mars_photos.*
import com.spaceapp.data.datasource.local.mars_photos.db.room.dao.MarsPhotoDao
import com.spaceapp.data.datasource.local.people_in_space.*
import com.spaceapp.data.datasource.local.people_in_space.db.room.dao.PeopleInSpaceDao
import com.spaceapp.data.datasource.local.space_news.*
import com.spaceapp.data.datasource.local.space_news.db.room.dao.SpaceNewsDao
import com.spaceapp.data.datasource.local.weather_condition.*
import com.spaceapp.data.datasource.local.weather_condition.db.room.dao.WeatherConditionDao
import com.spaceapp.data.datasource.local.where_is_the_iss.*
import com.spaceapp.data.datasource.local.where_is_the_iss.db.room.dao.IssDao
import com.spaceapp.data.datasource.remote.apod.*
import com.spaceapp.data.datasource.remote.apod.api.ApodApi
import com.spaceapp.data.datasource.remote.auth.firebase.*
import com.spaceapp.data.datasource.remote.auth.hms.*
import com.spaceapp.data.datasource.remote.mars_photos.*
import com.spaceapp.data.datasource.remote.mars_photos.api.MarsPhotoApi
import com.spaceapp.data.datasource.remote.people_in_space.*
import com.spaceapp.data.datasource.remote.people_in_space.api.PeopleInSpaceRightNowApi
import com.spaceapp.data.datasource.remote.space_news.*
import com.spaceapp.data.datasource.remote.space_news.api.SpaceNewsApi
import com.spaceapp.data.datasource.remote.weather_condition.*
import com.spaceapp.data.datasource.remote.weather_condition.api.CurrentWeatherApi
import com.spaceapp.data.datasource.remote.where_is_the_iss.*
import com.spaceapp.data.datasource.remote.where_is_the_iss.api.WhereIsTheIssApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideGMSAuthDataSource(): FirebaseAuthDataSource {
        return FirebaseAuthDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideHMSAuthDataSource(): HmsAuthDataSource {
        return HmsAuthDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideLocalApodDataSource(dao: ApodDao): ApodLocalDataSource {
        return ApodLocalDataSourceImpl(dao)
    }

    @Singleton
    @Provides
    fun provideRemoteApodDataSource(api: ApodApi): ApodRemoteDataSource {
        return ApodRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideLocalMarsPhotoDataSource(dao: MarsPhotoDao): MarsPhotosLocalDataSource {
        return MarsPhotosLocalDataSourceImpl(dao)
    }

    @Singleton
    @Provides
    fun provideRemoteMarsPhotoDataSource(api: MarsPhotoApi): MarsPhotoRemoteDataSource {
        return MarsPhotoRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideLocalPeopleInSpaceDataSource(dao: PeopleInSpaceDao): PeopleInSpaceLocalDataSource {
        return PeopleInSpaceLocalDataSourceImpl(dao)
    }

    @Singleton
    @Provides
    fun provideRemotePeopleInSpaceDataSource(api: PeopleInSpaceRightNowApi): PeopleInSpaceRemoteDataSource {
        return PeopleInSpaceRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideLocalSpaceNewsDataSource(dao: SpaceNewsDao): SpaceNewsLocalDataSource {
        return SpaceNewsLocalDataSourceImpl(dao)
    }

    @Singleton
    @Provides
    fun provideRemoteSpaceNewsDataSource(api: SpaceNewsApi): SpaceNewsRemoteDataSource {
        return SpaceNewsRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideLocalWeatherConditionDataSource(dao: WeatherConditionDao): WeatherConditionLocalDataSource {
        return WeatherConditionLocalDataSourceImpl(dao)
    }

    @Singleton
    @Provides
    fun provideRemoteWeatherConditionDataSource(api: CurrentWeatherApi): WeatherConditionRemoteDataSource {
        return WeatherConditionRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideLocalWhereIsTheIssDataSource(dao: IssDao): WhereIsTheIssLocalDataSource {
        return WhereIsTheIssLocalDataSourceImpl(dao)
    }

    @Singleton
    @Provides
    fun provideRemoteWhereIsTheIssDataSource(api: WhereIsTheIssApi): WhereIsTheIssRemoteDataSource {
        return WhereIsTheIssRemoteDataSourceImpl(api)
    }

    @Singleton
    @Provides
    fun provideExploreGalaxyDataLocalDataSource(): ExploreGalaxyDataSource {
        return ExploreGalaxyLocalDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideGlossaryLocalDataSource(): GlossaryLocalDataSource {
        return GlossaryLocalDataSourceImpl()
    }
}