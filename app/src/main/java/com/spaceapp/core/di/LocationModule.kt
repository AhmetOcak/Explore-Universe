package com.spaceapp.core.di

import com.spaceapp.data.datasource.remote.location.ILocationManager
import com.spaceapp.data.datasource.remote.location.gms.LocationTrackerGms
import com.spaceapp.data.datasource.remote.location.hms.LocationTrackerHms
import com.spaceapp.data.datasource.remote.location.ILocationTracker
import com.spaceapp.data.datasource.remote.location.LocationManager
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
    abstract fun bindHmsLocationTracker(defaultLocationTrackerHms: LocationTrackerHms): ILocationTracker

    @Binds
    @Singleton
    abstract fun bindGmsLocationTracker(defaultLocationTrackerGms: LocationTrackerGms): ILocationTracker

    @Binds
    @Singleton
    abstract fun bindLocationManager(locationManager: LocationManager) : ILocationManager
}