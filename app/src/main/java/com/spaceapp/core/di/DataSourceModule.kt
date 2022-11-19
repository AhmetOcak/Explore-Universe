package com.spaceapp.core.di

import com.spaceapp.data.datasource.local.apod.*
import com.spaceapp.data.datasource.local.explore_galaxy_data.*
import com.spaceapp.data.datasource.local.glossary_data.*
import com.spaceapp.data.datasource.local.mars_photos.*
import com.spaceapp.data.datasource.local.people_in_space.*
import com.spaceapp.data.datasource.local.space_news.*
import com.spaceapp.data.datasource.local.weather_condition.*
import com.spaceapp.data.datasource.local.where_is_the_iss.*
import com.spaceapp.data.datasource.remote.apod.*
import com.spaceapp.data.datasource.remote.auth.firebase.*
import com.spaceapp.data.datasource.remote.auth.hms.*
import com.spaceapp.data.datasource.remote.mars_photos.*
import com.spaceapp.data.datasource.remote.people_in_space.*
import com.spaceapp.data.datasource.remote.space_news.*
import com.spaceapp.data.datasource.remote.weather_condition.*
import com.spaceapp.data.datasource.remote.where_is_the_iss.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindFirebaseAuthDataSource(firebaseAuthDataSourceImpl: FirebaseAuthDataSourceImpl) : FirebaseAuthDataSource

    @Binds
    @Singleton
    abstract fun bindHmsAuthDataSource(hmsAuthDataSourceImpl: HmsAuthDataSourceImpl) : HmsAuthDataSource

    @Binds
    @Singleton
    abstract fun bindLocalApodDataSource(apodLocalDataSourceImpl: ApodLocalDataSourceImpl) : ApodLocalDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteApodDataSource(apodRemoteDataSourceImpl: ApodRemoteDataSourceImpl) : ApodRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalMarsPhotoDataSource(marsPhotosLocalDataSourceImpl: MarsPhotosLocalDataSourceImpl) : MarsPhotosLocalDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteMarsPhotoDataSource(marsPhotoRemoteDataSourceImpl: MarsPhotoRemoteDataSourceImpl) : MarsPhotoRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalPeopleInSpaceDataSource(peopleInSpaceLocalDataSourceImpl: PeopleInSpaceLocalDataSourceImpl) : PeopleInSpaceLocalDataSource

    @Binds
    @Singleton
    abstract fun bindRemotePeopleInSpaceDataSource(peopleInSpaceRemoteDataSourceImpl: PeopleInSpaceRemoteDataSourceImpl) : PeopleInSpaceRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalSpaceNewsDataSource(spaceNewsLocalDataSourceImpl: SpaceNewsLocalDataSourceImpl) : SpaceNewsLocalDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteSpaceNewsDataSource(spaceNewsRemoteDataSourceImpl: SpaceNewsRemoteDataSourceImpl) : SpaceNewsRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalWeatherConditionDataSource(weatherConditionLocalDataSourceImpl: WeatherConditionLocalDataSourceImpl) : WeatherConditionLocalDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteWeatherConditionDataSource(weatherConditionRemoteDataSourceImpl: WeatherConditionRemoteDataSourceImpl) : WeatherConditionRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalWhereIsTheIssDataSource(whereIsTheIssLocalDataSourceImpl: WhereIsTheIssLocalDataSourceImpl) : WhereIsTheIssLocalDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteWhereIsTheIssDataSource(whereIsTheIssRemoteDataSourceImpl: WhereIsTheIssRemoteDataSourceImpl) : WhereIsTheIssRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindExploreGalaxyDataLocalDataSource(exploreGalaxyLocalDataSourceImpl: ExploreGalaxyLocalDataSourceImpl) : ExploreGalaxyDataSource

    @Binds
    @Singleton
    abstract fun bindGlossaryLocalDataSource(glossaryLocalDataSourceImpl: GlossaryLocalDataSourceImpl) : GlossaryLocalDataSource
}