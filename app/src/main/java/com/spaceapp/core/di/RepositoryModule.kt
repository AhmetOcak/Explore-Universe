package com.spaceapp.core.di

import com.spaceapp.data.datasource.local.apod.ApodLocalDataSource
import com.spaceapp.data.datasource.local.explore_galaxy_data.ExploreGalaxyDataSource
import com.spaceapp.data.datasource.local.glossary_data.GlossaryLocalDataSource
import com.spaceapp.data.datasource.local.mars_photos.MarsPhotosLocalDataSource
import com.spaceapp.data.datasource.local.people_in_space.PeopleInSpaceLocalDataSource
import com.spaceapp.data.datasource.local.space_news.SpaceNewsLocalDataSource
import com.spaceapp.data.datasource.local.weather_condition.WeatherConditionLocalDataSource
import com.spaceapp.data.datasource.local.where_is_the_iss.WhereIsTheIssLocalDataSource
import com.spaceapp.data.datasource.remote.apod.ApodRemoteDataSource
import com.spaceapp.data.datasource.remote.auth.firebase.FirebaseAuthDataSource
import com.spaceapp.data.datasource.remote.auth.hms.HmsAuthDataSource
import com.spaceapp.data.datasource.remote.mars_photos.MarsPhotoRemoteDataSource
import com.spaceapp.data.datasource.remote.people_in_space.PeopleInSpaceRemoteDataSource
import com.spaceapp.data.datasource.remote.space_news.SpaceNewsRemoteDataSource
import com.spaceapp.data.datasource.remote.weather_condition.WeatherConditionRemoteDataSource
import com.spaceapp.data.datasource.remote.where_is_the_iss.WhereIsTheIssRemoteDataSource
import com.spaceapp.data.repository.*
import com.spaceapp.domain.repository.AuthRepository
import com.spaceapp.data.repository.AuthRepositoryImpl
import com.spaceapp.domain.repository.WhereIsTheIssRepository
import com.spaceapp.data.repository.WhereIsTheIssRepositoryImpl
import com.spaceapp.domain.repository.NasaRepository
import com.spaceapp.data.repository.NasaRepositoryImpl
import com.spaceapp.domain.repository.PeopleInSpaceRepository
import com.spaceapp.data.repository.PeopleInSpaceRepositoryImpl
import com.spaceapp.domain.repository.SpaceNewsRepository
import com.spaceapp.data.repository.SpaceNewsRepositoryImpl
import com.spaceapp.domain.repository.SpaceObjectsRepository
import com.spaceapp.data.repository.SpaceObjectsRepositoryImpl
import com.spaceapp.domain.repository.WeatherConditionRepository
import com.spaceapp.data.repository.WeatherConditionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePeopleInSpaceRepository(
        localDataSource: PeopleInSpaceLocalDataSource,
        remoteDataSource: PeopleInSpaceRemoteDataSource
    ): PeopleInSpaceRepository {
        return PeopleInSpaceRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideSpaceNewsRepository(
        localDataSource: SpaceNewsLocalDataSource,
        remoteDataSource: SpaceNewsRemoteDataSource
    ): SpaceNewsRepository {
        return SpaceNewsRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideWeatherConditionRepository(
        localDataSource: WeatherConditionLocalDataSource,
        remoteDataSource: WeatherConditionRemoteDataSource
    ): WeatherConditionRepository {
        return WeatherConditionRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideWhereIsTheIssRepository(
        localDataSource: WhereIsTheIssLocalDataSource,
        remoteDataSource: WhereIsTheIssRemoteDataSource
    ): WhereIsTheIssRepository {
        return WhereIsTheIssRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideNasaRepository(
        remoteApodDataSource: ApodRemoteDataSource,
        localApodDataSource: ApodLocalDataSource,
        localMarsDataSource: MarsPhotosLocalDataSource,
        remoteMarsDataSource: MarsPhotoRemoteDataSource
    ): NasaRepository {
        return NasaRepositoryImpl(
            remoteApodDataSource,
            localApodDataSource,
            localMarsDataSource,
            remoteMarsDataSource
        )
    }

    @Singleton
    @Provides
    fun provideAuthRepository(
        gmsAuthDataSource: FirebaseAuthDataSource,
        hmsAuthDataSource: HmsAuthDataSource
    ): AuthRepository {
        return AuthRepositoryImpl(gmsAuthDataSource, hmsAuthDataSource)
    }

    @Singleton
    @Provides
    fun provideSpaceObjectsRepository(
        exploreGalaxyLocalDataSource: ExploreGalaxyDataSource,
        glossaryLocalDataSource: GlossaryLocalDataSource
    ): SpaceObjectsRepository {
        return SpaceObjectsRepositoryImpl(exploreGalaxyLocalDataSource, glossaryLocalDataSource)
    }
}