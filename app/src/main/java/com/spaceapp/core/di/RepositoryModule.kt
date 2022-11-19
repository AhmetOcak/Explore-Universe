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
    abstract fun bindExploreGalaxyRepository(exploreGalaxyRepositoryImpl: ExploreGalaxyRepositoryImpl): ExploreGalaxyRepository

    @Binds
    @Singleton
    abstract fun bindGlossaryRepository(glossaryRepositoryImpl: GlossaryRepositoryImpl): GlossaryRepository

    @Binds
    @Singleton
    abstract fun bindApodRepository(apodRepositoryImpl: ApodRepositoryImpl): ApodRepository

    @Binds
    @Singleton
    abstract fun bindFirebaseAuthRepository(firebaseAuthRepositoryImpl: FirebaseAuthRepositoryImpl): FirebaseAuthRepository

    @Binds
    @Singleton
    abstract fun bindHmsAuthRepository(hmsAuthRepositoryImpl: HmsAuthRepositoryImpl): HmsAuthRepository

    @Binds
    @Singleton
    abstract fun bindMarsPhotoRepository(marsPhotosRepositoryImpl: MarsPhotosRepositoryImpl) : MarsPhotosRepository

    @Binds
    @Singleton
    abstract fun bindPeopleInSpaceRepository(peopleInSpaceRepositoryImpl: PeopleInSpaceRepositoryImpl) : PeopleInSpaceRepository

    @Binds
    @Singleton
    abstract fun bindSpaceNewsRepository(spaceRepositoryImpl: SpaceNewsRepositoryImpl) : SpaceNewsRepository

    @Binds
    @Singleton
    abstract fun bindWeatherConditionRepository(weatherConditionRepositoryImpl: WeatherConditionRepositoryImpl) : WeatherConditionRepository

    @Binds
    @Singleton
    abstract fun bindWhereIsTheIssRepository(whereIsTheIssRepositoryImpl: WhereIsTheIssRepositoryImpl) : WhereIsTheIssRepository
}