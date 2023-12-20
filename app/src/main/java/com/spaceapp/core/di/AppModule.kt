package com.spaceapp.core.di

import android.app.Application
import com.huawei.hms.location.FusedLocationProviderClient
import com.huawei.hms.location.LocationServices
import com.spaceapp.data.datasource.remote.apod.api.ApodApi
import com.spaceapp.data.datasource.remote.mars_photos.api.MarsPhotoApi
import com.spaceapp.data.datasource.remote.people_in_space.api.PeopleInSpaceRightNowApi
import com.spaceapp.data.datasource.remote.space_news.api.SpaceNewsApi
import com.spaceapp.data.datasource.remote.weather_condition.api.CurrentWeatherApi
import com.spaceapp.data.datasource.remote.where_is_the_iss.api.WhereIsTheIssApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePeopleInSpaceRightNowApi(): PeopleInSpaceRightNowApi {
        return Retrofit.Builder()
            .baseUrl("http://api.open-notify.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PeopleInSpaceRightNowApi::class.java)
    }

    @Singleton
    @Provides
    fun provideWhereIsTheIssApi(): WhereIsTheIssApi {
        return Retrofit.Builder()
            .baseUrl("https://api.wheretheiss.at/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WhereIsTheIssApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMarsPhotosApi(): MarsPhotoApi {
        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/mars-photos/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarsPhotoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSpaceNewsApi(): SpaceNewsApi {
        return Retrofit.Builder()
            .baseUrl("https://spacefo.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SpaceNewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCurrentWeatherApi(): CurrentWeatherApi {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrentWeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun provideApodApi(): ApodApi {
        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApodApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFusedLocationProviderClientHMS(application: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(application)
    }

    @Singleton
    @Provides
    fun provideFusedLocationProviderClientGMS(application: Application): com.google.android.gms.location.FusedLocationProviderClient {
        return com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(application)
    }
}