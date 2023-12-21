package com.spaceapp.core.di

import android.app.Application
import com.spaceapp.data.datasource.remote.location.ILocationManager
import com.spaceapp.data.datasource.remote.location.LocationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    @Singleton
    @Provides
    fun provideLocationManager(application: Application): ILocationManager {
        return LocationManager(application)
    }
}