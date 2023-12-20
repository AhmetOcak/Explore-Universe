package com.spaceapp.core.di

import com.spaceapp.data.repository.*
import com.spaceapp.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPeopleInSpaceRepository(peopleInSpaceRepositoryImpl: PeopleInSpaceRepositoryImpl): PeopleInSpaceRepository

    @Binds
    @Singleton
    abstract fun bindSpaceNewsRepository(spaceRepositoryImpl: SpaceNewsRepositoryImpl): SpaceNewsRepository

    @Binds
    @Singleton
    abstract fun bindWeatherConditionRepository(weatherConditionRepositoryImpl: WeatherConditionRepositoryImpl): WeatherConditionRepository

    @Binds
    @Singleton
    abstract fun bindWhereIsTheIssRepository(whereIsTheIssRepositoryImpl: WhereIsTheIssRepositoryImpl): WhereIsTheIssRepository

    @Binds
    @Singleton
    abstract fun bindNasaRepository(nasaRepositoryImpl: NasaRepositoryImpl): NasaRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindSpaceObjectsRepository(spaceObjectsRepositoryImpl: SpaceObjectsRepositoryImpl): SpaceObjectsRepository
}