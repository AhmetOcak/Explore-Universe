package com.spaceapp.core.di

import com.spaceapp.data.datasource.remote.location.gms.LocationTrackerGms
import com.spaceapp.data.datasource.remote.location.hms.LocationTrackerHms
import com.spaceapp.domain.repository.ILocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationTracker(defaultLocationTrackerHms: LocationTrackerHms): ILocationTracker

    @Binds
    @Singleton
    abstract fun bindLocationTracker(defaultLocationTrackerGms: LocationTrackerGms): ILocationTracker
}