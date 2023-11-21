package com.spaceapp.data.datasource.remote.location

import android.app.Application
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient as gmsProvider
import com.huawei.hms.location.FusedLocationProviderClient as hmsProvider
import com.google.android.gms.location.LocationServices as gmsServices
import com.huawei.hms.location.LocationServices as hmsServices
import com.spaceapp.core.common.helper.Device
import com.spaceapp.core.common.helper.MobileServiceType
import com.spaceapp.data.datasource.remote.location.gms.LocationTrackerGms
import com.spaceapp.data.datasource.remote.location.hms.LocationTrackerHms
import javax.inject.Inject

class LocationManager @Inject constructor(application: Application) : ILocationManager {

    private var hmsFusedLocationProviderClient: hmsProvider
    private var gmsFusedLocationProviderClient: gmsProvider

    private var location: ILocationTracker

    init {
        hmsFusedLocationProviderClient = hmsServices.getFusedLocationProviderClient(application.applicationContext)
        gmsFusedLocationProviderClient = gmsServices.getFusedLocationProviderClient(application.applicationContext)

        location = if (Device.mobileServiceType(context = application.applicationContext) == MobileServiceType.HMS) {
            LocationTrackerHms(
                locationClient = hmsFusedLocationProviderClient,
                application = application
            )
        } else {
            LocationTrackerGms(
                locationClient = gmsFusedLocationProviderClient,
                application = application
            )
        }
    }

    override suspend fun getCurrentLocation() : Location? = location.getCurrentLocation()
}